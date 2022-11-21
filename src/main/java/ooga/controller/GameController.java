package ooga.controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import ooga.model.collisions.collision_handling.CollisionChart;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.collisions.collision_handling.DefaultCollisionChart;
import ooga.view.nodes.NodeContainer;


/**
 * This is how the View and model will be able to communicate. This class
 * will be running during the gameplay.
 */
public class GameController {
    private ConnectionContainer container;
    private UserControlHandler controlHandler;
    private JSONInformationDecoder jsonDecoder;
    private DefaultCollisionChart collisionChart;

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController(String levelJSONPath, String collisionJSONPath, String controlsJSONPath) {
        controlHandler = new UserControlHandler();
        container = new ConnectionContainer();
        jsonDecoder = new JSONInformationDecoder();
        collisionChart = new DefaultCollisionChart();

        jsonDecoder.makeEntityContainerFromLevelJSON(levelJSONPath, container);
        // TODO: integrate new String for controls JSON into this constructor and in related locations in main and controller tests
        jsonDecoder.makeUserControlHandlerFromJSON(controlsJSONPath, controlHandler);
        jsonDecoder.makeCollisionChart
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
