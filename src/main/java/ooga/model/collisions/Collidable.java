package ooga.model.collisions;

import ooga.model.entities.Entity;

/**
 * Type of object that reacts to a collision (some objects, such as walls, do not react)
 */
public interface Collidable {

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   * @param other
   */
  void onCollision(Entity other, CollisionPhysicsInfo physicsInfo);

}
