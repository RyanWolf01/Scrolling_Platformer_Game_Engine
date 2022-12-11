package ooga.model.entities.extrainterfaces;

import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.entities.entitymodels.Entity;

/**
 * Type of object that reacts to a collision (some objects, such as walls, do not react)
 */
public interface Collidable {

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   * @param other Entity this object is colliding with
   */
  void onCollision(Entity other, CollisionPhysicsData physicsInfo);

}
