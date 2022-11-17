package ooga.model.collisions.collision_handling;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.collisions.CollisionPhysicsInfo;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartNotFoundException;
import ooga.model.entities.Entity;
import ooga.model.ImmutableInfo;

/**
 * Implements the CollisionHandler class and is the default class used for collision handling.
 */
public class DefaultCollisionHandler implements CollisionHandler {

  private final CollisionChartGetter myCollisionChartGetter;

  /**
   * Instantiates a new DefaultCollisionHandler. Creates and uses a DefaultCollisionChartReader on
   * initialization.
   */
  public DefaultCollisionHandler() {
    myCollisionChartGetter = new DefaultCollisionChartGetter();
  }

  /**
   * Instantiates a new DefaultCollisionHandler. Uses the CollisionChartReader passed as an argument
   * to the constructor
   *
   * @param collisionChartGetter the CollisionChartReader to be used
   */
  public DefaultCollisionHandler(CollisionChartGetter collisionChartGetter) {
    myCollisionChartGetter = collisionChartGetter;
  }

  /**
   * This handles the collision between two entities but ONLY acts on EntityA.
   *
   * @param entityA              the targetEntity
   * @param entityB              the entity that's informing the collision
   * @param collisionPhysicsInfo the physics data on this collision, such as which direction, from
   *                             the perspecitve of EntityA, that the collision occurred.
   */
  @Override
  public void handleCollision(Entity entityA, Entity entityB,
      CollisionPhysicsInfo collisionPhysicsInfo) {
    ImmutableInfo entityAInfo = entityA.getImmutableEntityInfo();
    ImmutableInfo entityBInfo = entityB.getImmutableEntityInfo();
    AliveAction action = getPostCollisionAction(entityAInfo, entityBInfo, collisionPhysicsInfo);

//    action.execute(entityA);
  }

  // Get the action that results from this collision. Will be called with perspective of targetEntityInfo
  private ImmutableActionDataContainer getPostCollisionAction(ImmutableInfo targetEntityInfo,
      ImmutableInfo sourceEntityInfo, ImmutableInfo collisionPhysicsInfo) {
    if (!targetEntityInfo.hasKey(ImmutableInfo.COLLISION_CHART_KEY)) {
      throw new CollisionChartNotFoundException("Target Entity doesn't have a collision chart!");
    }

    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
        ImmutableInfo.COLLISION_CHART_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, sourceEntityInfo, collisionPhysicsInfo);
    return collisionChart.getActionDatas(collisionData);
  }
}
