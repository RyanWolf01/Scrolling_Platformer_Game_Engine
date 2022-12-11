package ooga.model.entities.entitymodels;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;

public class BasicStaticCharacter extends StaticCharacter {

  /**
   * AutomaticMovingCharacter has lives but cannot move
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public BasicStaticCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
  }

}
