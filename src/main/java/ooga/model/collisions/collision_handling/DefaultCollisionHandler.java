package ooga.model.collisions.collision_handling;

import ooga.model.collisions.CollisionData;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntityInfo;

public class DefaultCollisionHandler implements CollisionHandler {

  @Override
  public void handleCollision(CollisionData collisionData, Entity entityA, Entity entityB) {
    // call getInfo on entities. Add that to collisionData
    ImmutableEntityInfo entityAInfo = entityA.getImmutableEntityInfo();

  }
}
