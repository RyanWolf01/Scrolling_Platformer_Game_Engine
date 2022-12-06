package ooga.model.entities.collidable;

import ooga.model.actionparsers.ActionParsingException;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import ooga.model.entities.info.ImmutableInfo;
import ooga.model.entities.info.Info;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.collisionhandling.exceptions.CollisionChartParsingException;
import ooga.model.collisions.actiondata.ActionDataContainer;

public abstract class CollidableEntity extends Entity implements Collidable {
  private final CollisionChart myCollisionChart;
  private final CurrentCollisionContainer myCurrentCollisions;

  public CollidableEntity(CollisionChart collisionChart, int initialXCoordinate,
      int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    myCollisionChart = collisionChart;
    myCurrentCollisions = new CurrentCollisionContainer();

  }

//  public CollidableEntity(int initialXCoordinate, int initialYCoordinate, double height,
//      double width, Info entityInfo) {
//    this(new DefaultCollisionChartGetter(), initialXCoordinate, initialYCoordinate, height, width,
//        entityInfo);
//  }

  @Override
  public void onCollision(Entity other, CollisionPhysicsData physicsInfo) {
    ActionDataContainer adc = getActionDatas(this.getImmutableEntityInfo(), other.getImmutableEntityInfo(), physicsInfo);

    int numActionsPerformed = performActions(adc);
    if (numActionsPerformed != adc.size()) {
      throw new ActionParsingException(String.format("%d actions were performed, but %d actions"
              + " should have been performed according to this entity's collision chart.",
          numActionsPerformed, adc.size()));
    }
  }

  protected abstract int performActions(ActionDataContainer actionDataContainer);

  //TODO: Come up with a better name (to express getting a "collection" of ActionData)
  protected ActionDataContainer getActionDatas(
      ImmutableInfo targetEntityInfo, ImmutableInfo otherEntityInfo,
      CollisionPhysicsData collisionPhysicsData) {

    if (!targetEntityInfo.hasKey(ImmutableInfo.TYPE_KEY)) {
      throw new CollisionChartParsingException("Target Entity's type isn't specified");
    }

//    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
//        ImmutableInfo.TYPE_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, otherEntityInfo,
        collisionPhysicsData);
    return myCollisionChart.getActionDatas(collisionData);
  }

//  @Override
//  public CollisionDirection getPreviousCollisionDirection(Entity otherEntity) {
////    return mySequentialCollisions.get(otherEntity);
//    return null;
//  }
//
//  @Override
//  public boolean wasPreviouslyColliding(Entity otherEntity) {
//    return mySequentialCollisions.containsKey(otherEntity);
//  }

  @Override
  public boolean hasCurrentCollisionWith(ImmutableEntity otherEntity) {
    return myCurrentCollisions.containsKey(otherEntity);
  }

  @Override
  public CollisionPhysicsData physicsDataOfCurrentCollisionWith(ImmutableEntity otherEntity) {
    return myCurrentCollisions.get(otherEntity);
  }

  // TODO: Fix this so that it's not just a getter returning a Map.
  @Override
  public CurrentCollisionContainer getMyCurrentCollisions() {
    return myCurrentCollisions;
  }
}

