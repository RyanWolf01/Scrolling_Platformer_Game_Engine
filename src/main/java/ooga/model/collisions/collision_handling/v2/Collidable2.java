package ooga.model.collisions.collision_handling.v2;

import ooga.model.ImmutableInfo;
import ooga.model.Info;
import ooga.model.actions.Action;
import ooga.model.collisions.CollisionPhysicsInfo;
import ooga.model.collisions.collision_handling.CollisionChart;
import ooga.model.collisions.collision_handling.CollisionChartGetter;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.collisions.collision_handling.DefaultCollisionChartGetter;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartNotFoundException;
import ooga.model.entities.Entity;

public abstract class Collidable2 extends Entity {

  private final CollisionChartGetter myCollisionChartGetter;

  public Collidable2(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    this(new DefaultCollisionChartGetter(), initialXCoordinate, initialYCoordinate, height, width,
        entityInfo);
  }

  public Collidable2(CollisionChartGetter collisionChartGetter, int initialXCoordinate,
      int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    myCollisionChartGetter = collisionChartGetter;
  }

  public void collide(Entity other, CollisionPhysicsInfo collisionPhysicsInfo) {
    ImmutableInfo myInfo = this.getImmutableInfo();
    ImmutableInfo otherEntityInfo = other.getImmutableInfo();
    Action action = getPostCollisionAction(myInfo, otherEntityInfo, collisionPhysicsInfo);

    executePostCollisionAction(action);
  }

  public abstract void executePostCollisionAction(Action action);

  private Action getPostCollisionAction(ImmutableInfo targetEntityInfo,
      ImmutableInfo sourceEntityInfo, ImmutableInfo collisionPhysicsInfo) {
    if (!targetEntityInfo.hasKey(ImmutableInfo.COLLISION_CHART_KEY)) {
      throw new CollisionChartNotFoundException("Target Entity doesn't have a collision chart!");
    }

    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
        ImmutableInfo.COLLISION_CHART_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, sourceEntityInfo,
        collisionPhysicsInfo);
    return collisionChart.getAction(collisionData);
  }
}