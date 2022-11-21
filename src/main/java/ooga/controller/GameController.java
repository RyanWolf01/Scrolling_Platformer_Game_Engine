package ooga.controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import ooga.view.nodes.NodeContainer;


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
        //jsonDecoder.makeUserControlHandlerFromJSON(controlsJSONFile, controlHandler);
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
