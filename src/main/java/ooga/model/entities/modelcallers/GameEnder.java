package ooga.model.entities.modelcallers;

import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;

public interface GameEnder {
  void endGame();

  void setEndGameCallable(EndGameCallable endGameCallable);
}
