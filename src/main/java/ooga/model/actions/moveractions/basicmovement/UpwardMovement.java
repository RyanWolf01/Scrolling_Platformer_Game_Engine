package ooga.model.actions.moveractions.basicmovement;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;

public class UpwardMovement implements MoverAction {

  private static final int UPWARD_VELOCITY = 5; // change this to properties file

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, UPWARD_VELOCITY);
    entity.move();
  }

}
