package ooga.model.actions.moveractions;

import ooga.model.entities.deadmovingentities.Mover;

public class StopYMovement implements MoverAction{

  /**
   * stops entity moveement in y direction
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.resetVelocities(false, true);
  }

}