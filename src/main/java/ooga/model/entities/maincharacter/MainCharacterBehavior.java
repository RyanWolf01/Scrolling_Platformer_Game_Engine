package ooga.model.entities.maincharacter;

import ooga.model.MainCharacterState;

public interface MainCharacterBehavior extends ImmutableMainCharacterBehavior{

  MainCharacterState getMainCharacterState();
  void checkLivesAndUpdateMainCharacterState();
  void setMainCharacterState(MainCharacterState mainCharacterState);
  void updateScore(double addToScore);
  double getScore();

}
