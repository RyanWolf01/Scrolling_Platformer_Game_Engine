package ooga.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.input.KeyCode;
import ooga.model.Model;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;


/**
 * This is how the View and model will be able to communicate. This class
 * will be running during the gameplay.
 */
public class GameController {
    private ConnectionContainer container;
    private UserControlHandler controlHandler;
    private JSONInformationDecoder jsonDecoder;
    private DefaultCollisionChart collisionChart;
    private Model model;

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController(String levelJSON, String collisionJSON, String controlsJSON) {
        controlHandler = new UserControlHandler();
        jsonDecoder = new JSONInformationDecoder(levelJSON, collisionJSON, controlsJSON);
        container = new ConnectionContainer(jsonDecoder);
        jsonDecoder.makeEntityContainerFromLevelJSON(container);
        // TODO: integrate new String for controls JSON into this constructor and in related locations in main and controller tests
        jsonDecoder.makeUserControlHandlerFromJSON(controlHandler);
        model = new Model(container.entities());
    }

    /**
     * Called by the View when it wants to refresh what is being displayed at the end of every animation
     * @return NodeContainer that the View can
     */
    public NodeContainer step(){
        checkForCollisions();
        model.step();
        container.update();
        return container.viewables();
    }

    public void handleKeyInput(KeyCode code){
        if(controlHandler.isMoveAction(code)){
            model.handleMoveKey(controlHandler.getMoverAction(code));
        }
        else if(controlHandler.isAliveAction(code)){
            model.handleAliveKey(controlHandler.getAliveAction(code));
        }
    }

    /**
     * Check for collisions in the View nodes
     * TODO: Refactor code (don't use getter for getMySequentialCollisions)
     */
    private void checkForCollisions(){
        model.preCollisionDetectionLoop();
        NodeContainer nodes = container.viewables();
        for(ScrollingNode collider: nodes){
            for(ScrollingNode collided: nodes){
                if(collider.getBoundsInParent().intersects(collided.getBoundsInParent()) && collided != collider && container.isCollidable(collider)){
                    model.handleCollision(container.getConnectedEntity(collider), container.getConnectedEntity(collided));
                }
            }
        }
        model.postCollisionDetectionLoop();
    }

}
