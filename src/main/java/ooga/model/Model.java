package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.Main;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.collisions.physics.GravityEnforcer;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.BackendContainer;

import java.util.ResourceBundle;

/**
 * Backend logic is performed in here,
 * methods are called by the controller
 */
public class Model {
  private final GravityEnforcer gravityEnforcer;
  public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Entities");
  public static final ResourceBundle containerResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Containers");
  BackendContainer entities;

  public Model(BackendContainer entities){
    this.entities = entities;
    gravityEnforcer = new GravityEnforcer(entities);
  }

  public void moveMovers(){
    gravityEnforcer.applyGravityToAllMovers();
    entities.automaticMovers().moveAll(); // move all automatic movers
    entities.mainCharacter().move();
  }

  /**
   * reset horizontal velocity of all Movers. This needs to be done after every horizontal movement
   */
  public void resetHorizontalVelocities(){
    entities.automaticMovers().resetVelocities(true, false);
    entities.mainCharacter().resetVelocities(true, false);
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
    removeNonFreshEntities();
    for (CollidableEntity collidable : entities.collidables()) {
      CurrentCollisionContainer currCollisions = collidable.getMyCurrentCollisions();
      for (ImmutableEntity otherEntity : collidable.getMyCurrentCollisions()) {
        currCollisions.get(otherEntity).setCollisionIsFresh(false);
      }
    }
  }

  private void removeNonFreshEntities() {
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
        collidable.onCollision(collided, new PhysicsCalculator().calculatePhysicsInfo(collider, collided));
      }
    }
  }

  private void handleCollisionHelper(Entity collider, Entity collided, CollisionPhysicsInfo currCollisionPhysicsInfo) {
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        collidable.onCollision(collided, new PhysicsCalculator().updatePhysicsInfoOfCurrentCollision(currCollisionPhysicsInfo));
      }
    }
  }

}
