package ooga.model.actions.moveractions;

import ooga.model.entities.movement.Mover;

/**
 * action that executes on a moving entity
 */
public interface MoverAction {

  /**
   * executes on a moving entity
   * @param entity on which action is executing
   */
  void execute(Mover entity);

}
