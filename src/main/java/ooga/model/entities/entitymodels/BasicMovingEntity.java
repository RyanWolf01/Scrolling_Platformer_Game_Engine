package ooga.model.entities.entitymodels;

import ooga.model.actionparsers.MoverActionParser;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.movement.MovementQueue;

public class BasicMovingEntity extends MovingEntity {

  /**
   * AutomaticMovingCharacter has lives but cannot move
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public BasicMovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo, movementQueue);
  }

  /**
   *
   * @param actionDataContainer contains actions to be applied to entity
   * @return number of actions performed
   */
  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
  }

}
