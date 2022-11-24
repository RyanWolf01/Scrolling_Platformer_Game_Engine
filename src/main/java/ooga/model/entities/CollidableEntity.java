package ooga.model.entities;

import java.util.HashMap;
import ooga.model.actionparsers.ActionParsingException;
import ooga.model.collisions.physics.CollisionDirection;
import ooga.model.entities.data.ImmutableInfo;
import ooga.model.entities.data.Info;
import ooga.model.collisions.Collidable;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.collisionhandling.exceptions.CollisionChartParsingException;
import ooga.model.collisions.actiondata.ActionDataContainer;

public abstract class CollidableEntity extends Entity implements Collidable {
  private final CollisionChart myCollisionChart;
  private HashMap<Entity, CollisionDirection> previousCollisions;

  public CollidableEntity(CollisionChart collisionChart, int initialXCoordinate,
      int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    myCollisionChart = collisionChart;
  }

//  public CollidableEntity(int initialXCoordinate, int initialYCoordinate, double height,
//      double width, Info entityInfo) {
//    this(new DefaultCollisionChartGetter(), initialXCoordinate, initialYCoordinate, height, width,
//        entityInfo);
//  }

  @Override
  public void onCollision(Entity other, CollisionPhysicsInfo physicsInfo) {
    ActionDataContainer adc = getActionDatas(this.getImmutableEntityInfo(),
        other.getImmutableEntityInfo(), physicsInfo);

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
      CollisionPhysicsInfo collisionPhysicsInfo) {

    if (!targetEntityInfo.hasKey(ImmutableInfo.TYPE_KEY)) {
      throw new CollisionChartParsingException("Target Entity's type isn't specified");
    }

//    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
//        ImmutableInfo.TYPE_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, otherEntityInfo,
        collisionPhysicsInfo);
    return myCollisionChart.getActionDatas(collisionData);
  }

  @Override
  public CollisionDirection getPreviousCollisionDirection(Entity otherEntity) {
    return previousCollisions.get(otherEntity);
  }

  @Override
  public boolean wasPreviouslyColliding(Entity otherEntity) {
    return previousCollisions.containsKey(otherEntity);
  }
}

