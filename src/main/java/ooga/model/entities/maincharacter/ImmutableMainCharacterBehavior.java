package ooga.model.entities.maincharacter;

public interface ImmutableMainCharacterBehavior {

  /**
   *
   * @return score
   */
  int getScore();
  /**
   *
   * @return get MainCharacter state
   */
  MainCharacterState getMainCharacterState();

  /**
   * @return if has power up
   */
  boolean hasPowerUp();

}
