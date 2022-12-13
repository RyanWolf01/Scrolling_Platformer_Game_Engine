package ooga.model.actions.modelactions;

import ooga.model.entities.modelcallers.GameEnder;

public class EndGameAction {
  public void execute(GameEnder gameEnder) {
    gameEnder.endGame(false);
  }
}
