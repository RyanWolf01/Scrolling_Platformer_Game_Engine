package ooga.model.collisions.collision_handling;

import ooga.model.actions.Action;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartNotFoundException;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntityInfo;

public class DefaultCollisionHandler implements CollisionHandler {

  private final CollisionChartReader myCollisionChartReader;

  public DefaultCollisionHandler() {
    myCollisionChartReader = new CollisionChartReader();
  }

  @Override
  public void handleCollision(Entity entityA, Entity entityB, CollisionPhysicsData collisionPhysicsData) {
    ImmutableEntityInfo entityAInfo = entityA.getImmutableEntityInfo();
    ImmutableEntityInfo entityBInfo = entityB.getImmutableEntityInfo();
    Action action = getPostCollisionAction(entityAInfo, entityBInfo, collisionPhysicsData);

    action.execute(entityA);
  }

  public Action getPostCollisionAction(ImmutableEntityInfo targetEntityInfo, ImmutableEntityInfo sourceEntityInfo, CollisionPhysicsData collisionPhysicsData) {
    if (! targetEntityInfo.hasKey(ImmutableEntityInfo.COLLISION_CHART_KEY)) {
      throw new CollisionChartNotFoundException("Target Entity doesn't have a collision chart!");
    }

    CollisionChart collisionChart = myCollisionChartReader.getCollisionChart(targetEntityInfo.get(ImmutableEntityInfo.COLLISION_CHART_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, sourceEntityInfo, collisionPhysicsData);
    return collisionChart.getAction(collisionData);
  }
}
