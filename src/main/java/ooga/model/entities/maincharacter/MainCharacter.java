package ooga.model.entities.maincharacter;

public interface MainCharacter extends MainCharacterBehavior{

  void setMainCharacterBehavior(MainCharacterBehavior mainCharacterBehavior);
  ImmutableMainCharacterBehavior getMainCharacterBehavior();

}
