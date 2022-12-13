package ooga.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javafx.scene.input.KeyCode;
import ooga.controller.saveloadhandling.CheckpointDirectory;

import ooga.controller.saveloadhandling.LevelJSONRetriever;
import ooga.model.GameState;
import ooga.model.Model;
import ooga.view.ViewInfo;
import ooga.view.nodes.NodeContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


/**
 * This is how the View and model will be able to communicate. This class
 * will be running during the gameplay.
 */
public class GameController {
    private static final Logger LOG = LogManager.getLogger(GameController.class);
    private ConnectionContainer container;
    private UserControlHandler controlHandler;
    private JSONInformationDecoder jsonDecoder;
    private Model model;
    private Queue<KeyCode> keyCodeQueue;
    private String myLevel;
    private String playerName = "user";
    private String language;
    private boolean gameRunning = true;

    private JSONObject initialLevelJSON;
    private JSONObject initialCollisionJSON;
    private JSONObject initialControlsJSON;

    private DatabaseAccess access;

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController(String levelJSON, String collisionJSON, String controlsJSON) {
        myLevel = levelJSON;
        controlHandler = new UserControlHandler();
        jsonDecoder = new JSONInformationDecoder(levelJSON, collisionJSON, controlsJSON);
        container = new ConnectionContainer(jsonDecoder);
        jsonDecoder.makeEntityContainerFromLevelJSON(container);
        jsonDecoder.makeUserControlHandlerFromJSON(controlHandler);

        try {
            initialControlsJSON = jsonDecoder.initialJSONInformation(controlsJSON);
            initialCollisionJSON = jsonDecoder.initialJSONInformation(collisionJSON);
            initialLevelJSON = jsonDecoder.initialJSONInformation(levelJSON);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        access = new DatabaseAccess(jsonDecoder.game());

        model = new Model(container.entities());
        keyCodeQueue = new LinkedList<>();

    }

    /**
     * Called by the View when it wants to refresh what is being displayed at the end of every animation
     * @return NodeContainer that the View can
     */
    public NodeContainer step(){
        if (gameRunning){
            model.checkAndHandleGameState();
            checkAndHandleModelState();
            model.checkForAndHandleCollisions();
            executeKeyInputActions();
            model.moveMovers();
        }
        container.update();
        return container.viewables();
    }

    public void setHighScore(int score){
        access.postHighScore(playerName, score);
    }

    public Map<Integer, String> getHighScores(){
        Map<Integer, String> scoreMap = new HashMap<>();
        org.json.JSONObject json = access.getHighScores();

        for(String o : json.keySet()){
            scoreMap.put((Integer) json.get(o), o);
        }

        return scoreMap;
    }

    public void handleKeyInput(KeyCode code){
        if (! keyCodeQueue.contains(code)) {
            keyCodeQueue.add(code);
        }
    }

    /**
     * Gets information from the JSON about the
     * @return viewInfo
     */
    public ViewInfo getViewInfo(){
        return jsonDecoder.viewInfo();
    }

    /**
     * Method to be called on the button press in the frontend to save the game
     * at the current time by creating a checkpoint directory
     *
     * @param directoryName, string that the directory will be named
     */
    public void saveGame(String directoryName) {
        LevelJSONRetriever retriever = new LevelJSONRetriever(jsonDecoder.generalInfoMap, container.entities()
            .entities(), container.entities().livers());
        CheckpointDirectory saveDirectory = new CheckpointDirectory(directoryName, retriever.currentLevelJSON,
            initialControlsJSON, initialCollisionJSON);
        saveDirectory.CreateDirectory();
    }

    private void checkAndHandleModelState() {
        if (model.getGameState().equals(GameState.USER_WON)) {
            endGame(true);
        }
        else if (model.getGameState().equals(GameState.USER_LOST)) {
            endGame(false);
        }
    }

    private void executeKeyInputActions() {
        while (! keyCodeQueue.isEmpty()) {
            KeyCode code = keyCodeQueue.poll();
            if(controlHandler.isMoveAction(code)){
                model.handleMoveKey(controlHandler.getMoverAction(code));
            }
            else if(controlHandler.isAliveAction(code)){
                model.handleAliveKey(controlHandler.getAliveAction(code));
            }
        }
    }

    private void endGame(boolean userWon) {
        gameRunning = false;
        if (userWon) LOG.info("The user WON the game!");
        else LOG.info("The user LOST the game!");
    }

    public String getLevelDirectory(){
        return myLevel.replace("level.json", "");
    }

    public int getPlayerScore() {
        return 0;
    }

    public int getMainCharacterLives(){
        return container.entities().mainCharacter().getLives();

    }
}
