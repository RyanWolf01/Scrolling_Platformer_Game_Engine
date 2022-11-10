package ooga.model.collisions.collision_handling;

import ooga.model.actions.Action;
import ooga.model.collisions.CollisionData;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntityInfo;

public class DefaultCollisionHandler implements CollisionHandler {

  private PostCollisionActionGetter actionGetter;

  public DefaultCollisionHandler() {
    actionGetter = new PostCollisionActionGetter();
  }

  @Override
  public void handleCollision(Entity entityA, Entity entityB, CollisionData collisionData) {
    ImmutableEntityInfo entityAInfo = entityA.getImmutableEntityInfo();
    ImmutableEntityInfo entityBInfo = entityB.getImmutableEntityInfo();
    Action action = actionGetter.getPostCollisionAction(entityAInfo, entityBInfo, collisionData);

    action.execute(entityA);
  }
}
