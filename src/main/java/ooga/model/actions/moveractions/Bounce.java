package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.Mover;

public class Bounce implements MoverAction{

  private static final int BOUNCE_VELOCITY = Integer.parseInt(
      ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

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
