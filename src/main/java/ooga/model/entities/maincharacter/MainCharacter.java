package ooga.model.entities.maincharacter;

public interface MainCharacter extends MainCharacterBehavior{

  /**
   *
   * @param mainCharacterBehavior new main character behavior
   */
  void setMainCharacterBehavior(MainCharacterBehavior mainCharacterBehavior);

  /**
   *
   * @return immutable version of main character behavior
   */
  ImmutableMainCharacterBehavior getMainCharacterBehavior();

}
