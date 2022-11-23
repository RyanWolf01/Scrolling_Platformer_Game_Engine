package ooga.model.actions.moveractions;

import ooga.model.entities.movement.Mover;

public class Bounce implements MoverAction{

  private static final int BOUNCE_VELOCITY = -5; // change this to properties file

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, BOUNCE_VELOCITY);
    entity.move();
  }

}
