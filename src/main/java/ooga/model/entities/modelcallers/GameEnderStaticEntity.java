package ooga.model.entities.modelcallers;

import ooga.model.entities.StaticEntity;
import ooga.model.entities.info.Info;
import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;

public class GameEnderStaticEntity extends StaticEntity implements GameEnder {
  EndGameCallable endGameMethod;

  public GameEnderStaticEntity(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, EndGameCallable endGameMethod) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.endGameMethod = endGameMethod;
  }

  @Override
  public void endGame() {
    endGameMethod.execute();
  }
}
