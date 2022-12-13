package ooga.model.entities.maincharacter;

import ooga.model.MainCharacterState;

public interface MainCharacterBehavior {

  MainCharacterState getMainCharacterState();
  void checkNumLivesAndUpdateMyGameState();
  void setGameState(MainCharacterState mainCharacterState);
  void updateScore(double addToScore);
  int getScore();

}
