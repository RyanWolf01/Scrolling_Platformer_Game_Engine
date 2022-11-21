package ooga.model.entities.characters;

import ooga.model.actionparsers.MoverActionParser;
import ooga.model.entities.data.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.collisions.actiondata.ActionDataContainer;

public class BasicStaticCharacter extends StaticCharacter {

  /**
   * AutomaticMovingCharacter has lives and can move; moves automatically
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public BasicStaticCharacter(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
  }

//  private void performAliveAction(ActionDataContainer actionDataContainer) {
//    AliveActionParser aliveActionParser = new AliveActionParser(actionDataContainer);
//    if (aliveActionParser.hasAction()) {
//      AliveAction aliveAction = aliveActionParser.getAction();
//      aliveAction.execute(this);
//    }
//  }

}