package ooga.model.actions.maincharacteractions;

import ooga.model.entities.entitymodels.MainCharacter;

/**
 * action that executes on the MainCharacter
 */
public interface MainCharacterAction {

  /**
   * executes action on entity
   * @param entity on which action is executing
   */
  void execute(MainCharacter entity);

}
