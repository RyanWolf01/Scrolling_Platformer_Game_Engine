package ooga.model.actions.basicentityactions;

import ooga.model.entities.entitymodels.Entity;

/**
 * action that executes on the MainCharacter
 */
public interface BasicEntityAction {

  /**
   * executes action on entity
   * @param entity on which action is executing
   */
  void execute(Entity entity);

}
