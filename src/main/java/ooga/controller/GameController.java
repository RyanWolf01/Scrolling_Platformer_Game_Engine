package ooga.controller;

import java.nio.file.Path;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import ooga.model.collisions.collision_handling.CollisionHandler;
import ooga.model.entities.containers.ImmutableContainer;
import ooga.view.nodes.NodeContainer;

/**
 * This is how the View and model will be able to communicate. This class
 * will be running during the gameplay.
 */
public class GameController {
    private ImmutableContainer modelEntities;
    private CollisionHandler collisionHandler;

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController() {

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
        return null;
    }

    public void handleKeyInput(KeyCode code){

    }

    public void handleCollisions(Node collider, Node collided){

    }
}
