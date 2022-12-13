package ooga.model.actions.maincharacteractions;

import ooga.model.entities.entitymodels.BasicMainCharacter;

/**
 * action that executes on the MainCharacter
 */
public interface MainCharacterAction {

  /**
   * executes action on entity
   * @param basicMainCharacter on which action is executing
   */
  void execute(BasicMainCharacter basicMainCharacter);

}
