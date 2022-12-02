package ooga.model;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.BackendContainer;

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
  public void handleCollision(Entity collider, Entity collided){
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        collidable.onCollision(collided, new PhysicsCalculator().calculatePhysics(collider, collided));
      }
      if(collidable.equals(collided)){
        collidable.onCollision(collider, new PhysicsCalculator().calculatePhysics(collided, collider));
      }
    }
  }

  /**
   * Assumes that this Collision is one in a consecutive chain of previous collisions
   * @param collider
   * @param collided
   * @param prevCollisionPhysicsInfo
   */
  public void handleCollision(Entity collider, Entity collided, CollisionPhysicsInfo prevCollisionPhysicsInfo){
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        collidable.onCollision(collided, new PhysicsCalculator().calculatePhysics(collider, collided, prevCollisionPhysicsInfo));
      }
      if(collidable.equals(collided)){
        collidable.onCollision(collider, new PhysicsCalculator().calculatePhysics(collided, collider, prevCollisionPhysicsInfo));
      }
    }
  }

}
