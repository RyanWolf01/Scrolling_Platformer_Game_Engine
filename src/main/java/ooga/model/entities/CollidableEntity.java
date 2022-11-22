package ooga.model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.model.actionparsers.ActionParsingException;
import ooga.model.entities.data.ImmutableInfo;
import ooga.model.entities.data.Info;
import ooga.model.collisions.Collidable;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.collision_handling.CollisionChart;
import ooga.model.collisions.collision_handling.CollisionChartGetter;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.collisions.collision_handling.DefaultCollisionChartGetter;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartParsingException;
import ooga.model.collisions.actiondata.ActionDataContainer;

public abstract class CollidableEntity extends Entity implements Collidable {
  public static final List<String> COLLIDABLE_ENTITY_TYPES = new ArrayList<>(
      Arrays.asList("wall", "platform", "etc"));
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

    CollisionChart collisionChart = myCollisionChartGetter.getCollisionChart(targetEntityInfo.get(
        ImmutableInfo.TYPE_KEY));
    CollisionData collisionData = new CollisionData(targetEntityInfo, otherEntityInfo,
        collisionPhysicsInfo);
    return collisionChart.getActionDatas(collisionData);
  }
}

