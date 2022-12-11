package ooga.model.actions.aliveactions;

import ooga.model.entities.alive.Alive;

/**
 * action that executes on a living entity
 */
public interface AliveAction {

  /**
   * executes on a living entity
   * @param entity on which action is executing
   */
  void execute(Alive entity);

}
