package ooga.model.actions.moveractions;

import ooga.model.entities.movement.Mover;

public class Stop implements MoverAction{

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(-entity.getXVelocity(), -entity.getYVelocity());
    entity.move();
  }

}
