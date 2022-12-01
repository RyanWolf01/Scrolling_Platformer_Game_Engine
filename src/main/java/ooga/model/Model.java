package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.movement.AutomaticMover;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;

/**
 * Backend logic is performed in here,
 * methods are called by the controller
 */
public class Model {
  BackendContainer entities;

  public Model(BackendContainer entities){
    this.entities = entities;
  }

  public void step(){
    entities.automaticMovers().moveAll(); // move all automatic movers
    entities.mainCharacter().move();
  }

  public void handleMoveKey(MoverAction action){
    entities.mainCharacter().acceptMoveAction(action);
  }

  public void handleAliveKey(AliveAction action){
    entities.mainCharacter().acceptAliveAction(action);
  }

  /**
   * Handles the collision with backend logic
   * @param collider first entity that collides
   * @param collided second entity that is collided with
   */
  public void handleCollision(Entity collider, Entity collided) {
    if (collider.hasCurrentCollisionWith(collided)) {
      handleCollisionHelper(collider, collided, collider.physicsInfoOfCurrentCollisionWith(collided));
    }
    else {
      handleCollisionHelper(collider, collided);
    }
    collider.getMyCurrentCollisions().get(collided).setCollisionIsFresh(true);
  }

  public void preCollisionDetectionLoop() {
    for (CollidableEntity collidable : entities.collidables()) {
      CurrentCollisionContainer currCollisions = collidable.getMyCurrentCollisions();
      for (ImmutableEntity otherEntity : currCollisions) {
        currCollisions.get(otherEntity).setCollisionIsFresh(false);
      }
    }
  }

  public void postCollisionDetectionLoop() {
    for (CollidableEntity collidable : entities.collidables()) {
      CurrentCollisionContainer currCollisions = collidable.getMyCurrentCollisions();

      // from https://www.geeksforgeeks.org/convert-an-iterator-to-a-list-in-java/
      List<ImmutableEntity> keys = new ArrayList<>();
      currCollisions.iterator().forEachRemaining(keys::add);

      for (ImmutableEntity otherEntity : keys) {
        if (! currCollisions.get(otherEntity).collisionIsFresh()) {
          currCollisions.remove(otherEntity);
        }
      }
    }
  }

  private void handleCollisionHelper(Entity collider, Entity collided) {
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        collidable.onCollision(collided, new PhysicsCalculator().calculatePhysics(collider, collided));
      }
    }
  }

  /**
   * Assumes that this Collision is one in a consecutive chain of previous collisions
   * @param collider
   * @param collided
   * @param prevCollisionPhysicsInfo
   */
  private void handleCollisionHelper(Entity collider, Entity collided, CollisionPhysicsInfo prevCollisionPhysicsInfo) {
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        collidable.onCollision(collided, new PhysicsCalculator().calculatePhysics(collider, collided, prevCollisionPhysicsInfo));
      }
    }
  }

}
