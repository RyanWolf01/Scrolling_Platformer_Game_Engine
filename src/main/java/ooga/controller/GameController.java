package ooga.controller;

import java.io.File;
import java.util.Map;
import java.util.Set;
import javafx.scene.input.KeyCode;
import ooga.model.Model;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.entities.Entity;
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
        // make everything not fresh
        setAllIsFreshFalse(container.viewables());

        NodeContainer nodes = container.viewables();
        for(ScrollingNode collider: nodes){
            for(ScrollingNode collided: nodes){
                if(collider.getBoundsInParent().intersects(collided.getBoundsInParent()) && collided != collider && container.isCollidable(collider)){
                    // TODO: add entity b to entity a's list of things that it's collided with previously
                    Entity a = container.getConnectedEntity(collider);
                    Entity b = container.getConnectedEntity(collided);
                    if (a.hasSequentialCollisionWith(b)) {
                        model.handleCollision(a, b, a.physicsInfoOfSequentialCollisionWith(b));
                        a.getMySequentialCollisions().get(b).setCollisionIsFresh(true);
                    }
                    else {
                        model.handleCollision(a, b);
                    }
                }
            }
        }

        removeAllNotFresh(container.viewables());
        // remove everything that's not fresh
        // TODO: for every thing in entity a's list of things that it's collided with, remove all the
        // things that weren't added in this iteration
        // a's HashMap -->  otherEntity (key) : (isFresh [boolean], prevPhysicsInfo [isFresh, CollisionDirection, numConsecutiveCollisions])

       // here what we want to do is determine whether or not two things are colliding by
    }

    // TODO: Refactor this code
    private void setAllIsFreshFalse(NodeContainer nodeContainer) {
        for (ScrollingNode node : nodeContainer) {
            Entity entity = container.getConnectedEntity(node);
            Map<Entity, CollisionPhysicsInfo> map = entity.getMySequentialCollisions();
            for (Entity otherEntity : map.keySet()) {
                map.get(otherEntity).setCollisionIsFresh(false);
            }
        }
    }

    private void removeAllNotFresh(NodeContainer nodeContainer) {
        for (ScrollingNode node : nodeContainer) {
            Entity entity = container.getConnectedEntity(node);
            Map<Entity, CollisionPhysicsInfo> map = entity.getMySequentialCollisions();
            Entity[] keys = map.keySet().toArray(new Entity[0]);
            for (Entity otherEntity : keys) {
                if (! map.get(otherEntity).collisionIsFresh()) {
                    map.remove(otherEntity);
                }
            }
        }
    }

}
