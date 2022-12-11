package ooga.model.entities.modelcallers;

import ooga.model.actionparsers.EndGameActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.info.Info;
import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;

public class GameEnderCollidableEntity extends CollidableEntity implements GameEnder {
  EndGameCallable endGameMethod;

  public GameEnderCollidableEntity(CollisionChart collisionChart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(collisionChart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  @Override
  public void endGame() {
    if (endGameMethod == null) throw new RuntimeException("The end game method for mario hasn't"
        + "been set!");
    endGameMethod.execute();
  }

  @Override
  public void setEndGameCallable(EndGameCallable endGameCallable) {
    endGameMethod = endGameCallable;
  }

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    return new EndGameActionParser(actionDataContainer).parseAndApplyActions(this);
  }
}
