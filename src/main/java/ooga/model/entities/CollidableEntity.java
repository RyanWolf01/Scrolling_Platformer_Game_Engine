package ooga.model.entities;

import ooga.model.entities.data.ImmutableInfo;
import ooga.model.entities.data.Info;
import ooga.model.collisions.Collidable;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.collision_handling.CollisionChart;
import ooga.model.collisions.collision_handling.CollisionChartGetter;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.collisions.collision_handling.DefaultCollisionChartGetter;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartNotFoundException;
import ooga.model.collisions.actiondata.ActionDataContainer;

public abstract class CollidableEntity extends Entity implements Collidable {

  private final CollisionChartGetter myCollisionChartGetter;

  public CollidableEntity(CollisionChartGetter collisionChartGetter, int initialXCoordinate,
      int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    myCollisionChartGetter = collisionChartGetter;
  }

  public CollidableEntity(int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    this(new DefaultCollisionChartGetter(), initialXCoordinate, initialYCoordinate, height, width,
        entityInfo);
  }

  @Override
  public void onCollision(Entity other, CollisionPhysicsInfo physicsInfo) {
    ActionDataContainer adc = getActionDatas(this.getImmutableEntityInfo(),
        other.getImmutableEntityInfo(), physicsInfo);
    performActions(adc);
  }

  protected abstract void performActions(ActionDataContainer actionDataContainer);

  //TODO: Come up with a better name (to express getting a "collection" of ActionData)
  protected ActionDataContainer getActionDatas(
      ImmutableInfo targetEntityInfo, ImmutableInfo otherEntityInfo,
      CollisionPhysicsInfo collisionPhysicsInfo) {

    if (!targetEntityInfo.hasKey(ImmutableInfo.COLLISION_CHART_KEY)) {
      throw new CollisionChartNotFoundException("Target Entity doesn't have a collision chart!");
    }

    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
        ImmutableInfo.COLLISION_CHART_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, otherEntityInfo,
        collisionPhysicsInfo);
    return collisionChart.getActionDatas(collisionData);
  }
}

