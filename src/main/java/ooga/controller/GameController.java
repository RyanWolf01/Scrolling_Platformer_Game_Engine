package ooga.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.input.KeyCode;
import ooga.controller.saveloadhandling.CheckpointDirectory;
import ooga.controller.saveloadhandling.LevelJSONRetriever;
import ooga.model.Model;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.entitymodels.Entity;
import ooga.view.ViewInfo;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;
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
    private String playerName;
    private String language;
    private boolean gameRunning = true;

    private JSONObject initialLevelJSON;
    private JSONObject initialCollisionJSON;
    private JSONObject initialControlsJSON;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        model = new Model(container.entities(), this::endGame);
        keyCodeQueue = new LinkedList<>();
    }

    /**
     * Called by the View when it wants to refresh what is being displayed at the end of every animation
     * @return NodeContainer that the View can
     */
    public NodeContainer step(){
        if (! gameRunning) return container.viewables();
        model.checkForAndHandleCollisions();
        executeKeyInputActions();
        model.moveMovers();
        container.update();
        return container.viewables();
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
     * @param directoryName
     */
    public void saveGame(String directoryName) {
        LevelJSONRetriever retriever = new LevelJSONRetriever(jsonDecoder.generalInfoMap, container.entities()
            .entities(), container.entities().livers());
        CheckpointDirectory saveDirectory = new CheckpointDirectory(directoryName, retriever.currentLevelJSON,
            initialControlsJSON, initialCollisionJSON);
        saveDirectory.CreateDirectory();
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

    private void endGame() {
        gameRunning = false;
        LOG.info("The game is over (and you lost the game)!");
    }

//    /**
//     * Check for collisions in the View nodes
//     */
//    private void checkForCollisions(){
//        model.preCollisionDetectionLoop();
//        NodeContainer nodes = container.viewables();
//        for(ScrollingNode collider: nodes){
//            for(ScrollingNode collided: nodes){
////                if(collider.getBoundsInParent().intersects(collided.getBoundsInParent()) && collided != collider && container.isCollidable(collider)){
////                    model.handleCollision(container.getConnectedEntity(collider), container.getConnectedEntity(collided));
////                }
//                if(container.isCollidable(collider) && new PhysicsCalculator().areColliding((CollidableEntity) container.getConnectedEntity(collider), container.getConnectedEntity(collided)) && collided != collider){
//                    model.handleCollision(container.getConnectedEntity(collider), container.getConnectedEntity(collided));
//                }
//                testStuff(collider, collided, container.getConnectedEntity(collider), container.getConnectedEntity(collided));
//
//                if(container.isCollidable(collider) && new PhysicsCalculator().areColliding(collider, collided) && collided != collider){
//                    model.handleCollision(container.getConnectedEntity(collider), container.getConnectedEntity(collided));
//                }
//            }
//        }
//    }
//
//    private void testStuff(ScrollingNode scrollingNodeA, ScrollingNode scrollingNodeB, Entity entityA, Entity entityB) {
//        boolean test1 = scrollingNodeA.getBoundsInParent().getMinX() == entityA.getXCoordinate();
//        boolean test2 = scrollingNodeB.getBoundsInParent().getMinX() == entityB.getXCoordinate();
//        boolean test3 = scrollingNodeA.getBoundsInParent().getMinY() == entityA.getYCoordinate();
//        boolean test4 = scrollingNodeB.getBoundsInParent().getMinY() == entityB.getYCoordinate();
//        boolean test5 = scrollingNodeA.getBoundsInParent().getHeight() == entityA.getHeight();
//        boolean test6 = scrollingNodeB.getBoundsInParent().getHeight() == entityB.getHeight();
//
//        if (!test5 || !test6) System.out.println("yeeeeeeeee");
//
//        int i = 0;
//        i += 1;
//    }


    public String getLevelDirectory(){
        return myLevel.replace("level.json", "");
    }

    public void setPlayerName(String name){
        LOG.info("Player name: " + name);
        playerName = name;
    }

    public void setLanguage(String textLanguage){
        LOG.info("Language: " + textLanguage);
        language = textLanguage;
    }
}
