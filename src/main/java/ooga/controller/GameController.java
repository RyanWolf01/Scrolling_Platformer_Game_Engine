package ooga.controller;

import java.nio.file.Path;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
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

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController() {
        container = new ConnectionContainer();
    }

    public void setupSimulation(Path configFilePath) {
        /*
        JSONInformationDecoder jsonDecoder = new JSONInformationDecoder();
        PreloadedCollisionChartGetter collisionChartGetter = new PreloadedCollisionChartGetter(jsonDecoder, configFilePath.entityFiles);
        CollisionHandler collisionHandler = new DefaultCollisionHandler(collisionChartGetter);
         */
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
        // eventually make map of keycodes to actions, then send that info to model
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
