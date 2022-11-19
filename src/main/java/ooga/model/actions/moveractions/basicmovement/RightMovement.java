package ooga.model.actions.moveractions.basicmovement;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;

public class RightMovement implements MoverAction {

  private static final int RIGHT_VELOCITY = 5; // change this to properties file

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(RIGHT_VELOCITY, 0);
    entity.resetVelocities(true, false);
    entity.move();
  }

}
