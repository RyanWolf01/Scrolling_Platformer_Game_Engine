package ooga.model.actions.moveractions;

import ooga.model.entities.movement.Mover;

public class StopYMovement implements MoverAction{

  /**
   * stops entity moveement in y direction
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, -entity.getXVelocity());
    entity.move();
  }

}