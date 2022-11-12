package ooga.model.collisions.collision_handling;

import ooga.model.collisions.CollisionPhysicsInfo;
import ooga.model.entities.Entity;

/**
 * This should be called whenever two Entities have collided. When this is called, EntityA will be
 * acted upon, and EntityB WILL NOT be acted upon. To act upon EntityB, call handleCollision while
 * flipping the parameters of entityA and entityB.
 */
public interface CollisionHandler {

  /**
   * This handles the collision between two entities but ONLY acts on EntityA.
   *
   * @param entityA              the targetEntity
   * @param entityB              the entity that's informing the collision
   * @param collisionPhysicsInfo the physics data on this collision, such as which direction, from
   *                             the perspective of EntityA, that the collision occurred.
   */
  void handleCollision(Entity entityA, Entity entityB, CollisionPhysicsInfo collisionPhysicsInfo);
}