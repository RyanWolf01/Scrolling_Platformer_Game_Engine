package ooga.model.entities.maincharacter;

public interface MainCharacterBehavior extends ImmutableMainCharacterBehavior{

  MainCharacterState getMainCharacterState();
  void checkLivesAndUpdateMainCharacterState();
  void setMainCharacterState(MainCharacterState mainCharacterState);
  void updateScore(int addToScore);
  int getScore();

}
