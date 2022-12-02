package ooga.model.actions.moveractions;

import ooga.model.entities.deadmovingentities.Mover;

public class StopBothDirections implements MoverAction{

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.resetVelocities(true, true);
    entity.move();
  }

}
