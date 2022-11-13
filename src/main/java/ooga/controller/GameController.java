package ooga.controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.ImmutableContainer;
import ooga.view.entity_types.NodeContainer;
import ooga.controller.JSONInformationDecoder;

/**
 * This is how the View and model will be able to communicate. This class
 * will be running during the gameplay.
 */
public class GameController {
    private ImmutableContainer modelEntities;

    /**
     * The GameController needs to have a mapping of backend to frontend objects
     *
     */
    public GameController(){

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
