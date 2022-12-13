package ooga.model.entities.maincharacter;

public interface MainCharacterBehavior extends ImmutableMainCharacterBehavior{


  /**
   *
   * @return get MainCharacter state
   */
  MainCharacterState getMainCharacterState();
  /**
   * check lives and alter state accordingly
   */
  void checkLivesAndUpdateMainCharacterState();

  /**
   *
   * @param mainCharacterState new main character state
   */
  void setMainCharacterState(MainCharacterState mainCharacterState);

  /**
   *
   * @param addToScore to add to score
   */
  void updateScore(int addToScore);

  /**
   *
   * @return score
   */
  int getScore();

}
