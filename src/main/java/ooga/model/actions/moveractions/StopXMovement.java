package ooga.model.actions.moveractions;

import ooga.model.entities.deadmovingentities.Mover;

public class StopXMovement implements MoverAction{

  /**
   * stops entity moveement in x direction
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.resetVelocities(true, false);
  }

}