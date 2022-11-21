package ooga.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.collision_handling.CollisionHandler;
import ooga.model.entities.containers.ImmutableContainer;
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

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController(String levelJSONPath, String collisionJSONPath) {
        controlHandler = new UserControlHandler();
        container = new ConnectionContainer();
        jsonDecoder = new JSONInformationDecoder();
        jsonDecoder.makeEntityContainerFromLevelJSON(levelJSONPath, container);
        // TODO: integrate new String for controls JSON into this constructor and in related locations in main and controller tests
        // jsonDecoder.makeUserControlHandlerFromJSON(controlsJSONPath, controlHandler);
    }

    /**
     * Called by the View when it wants to refresh what is being displayed at the end of every animation
     * @return NodeContainer that the View can
     */
    public NodeContainer step(){
        checkForCollisions();
        //model.step(container.getEntities())
        container.update();
        return container.viewables();
    }

    public void handleKeyInput(KeyCode code){

    }

    /**
     * Check for collisions in the View nodes
     */
    private void checkForCollisions(){
        NodeContainer nodes = container.viewables();
        for(Node collider: nodes){
            for(Node collided: nodes){
                if(collider.getBoundsInParent().intersects(collided.getBoundsInParent()) && collided != collider){ // collided
                    // model.handleCollision(container.getConnectedEntity(collider), container.getConnectedEntity(collided))
                }
            }
        }
    }
}
