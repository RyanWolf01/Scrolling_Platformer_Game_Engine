package ooga.model.entities.entitymodels;

import ooga.model.actionparsers.ActionParsingException;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.entities.extrainterfaces.Collidable;
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

    if (!targetEntityInfo.hasKey(ImmutableInfo.COLLIDABLE_TYPE_KEY)) {
      throw new CollisionChartParsingException("Target Entity's type isn't specified");
    }

    CollisionData collisionData = new CollisionData(targetEntityInfo, otherEntityInfo,
        collisionPhysicsData);
    return myCollisionChart.getActionDatas(collisionData);
  }

  @Override
  public boolean hasCurrentCollisionWith(ImmutableEntity otherEntity) {
    return myCurrentCollisions.containsKey(otherEntity);
  }

  @Override
  public CollisionPhysicsData physicsDataOfCurrentCollisionWith(ImmutableEntity otherEntity) {
    return myCurrentCollisions.get(otherEntity);
  }

  @Override
  public CurrentCollisionContainer getMyCurrentCollisions() {
    return myCurrentCollisions;
  }
}

