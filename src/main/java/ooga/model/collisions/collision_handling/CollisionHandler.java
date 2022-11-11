package ooga.model.collisions.collision_handling;

import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.entities.Entity;

public interface CollisionHandler {
  void handleCollision(Entity entityA, Entity entityB, CollisionPhysicsData collisionPhysicsData);
}