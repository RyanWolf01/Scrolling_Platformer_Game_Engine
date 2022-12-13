package ooga.controller;

import java.io.IOException;
import java.util.*;

import javafx.scene.input.KeyCode;
import ooga.controller.saveloadhandling.CheckpointDirectory;

import ooga.model.entities.maincharacter.MainCharacterState;

import ooga.controller.saveloadhandling.LevelJSONRetriever;

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

    public boolean isGameRunning(){
        return gameRunning;
    }

    public void setHighScore(String playerName, int score){
        access.postHighScore(playerName, score);
    }

    public Map<String, Integer> getHighScores(){
        Map<String, Integer> scoreMap = new HashMap<>();
        org.json.JSONObject json = access.getHighScores();

        for(String o : json.keySet()){
            scoreMap.put(o, (Integer) json.get(o));
        }

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        scoreMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        // taken from https://howtodoinjava.com/java/sort/java-sort-map-by-values/

        return reverseSortedMap;
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
        if (model.getGameState().equals(MainCharacterState.USER_WON)) {
            endGame(true);
        }
        else if (model.getGameState().equals(MainCharacterState.USER_LOST)) {
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
        return model.getPlayerScore();
    }

    public int getMainCharacterLives(){
        return container.entities().mainCharacter().getLives();

    }
}
