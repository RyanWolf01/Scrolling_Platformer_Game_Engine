package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;

public class DownwardMovement implements MoverAction {

  private static final int DOWNWARD_VELOCITY = Integer.parseInt(ResourceBundle.getBundle("properties/movement").getString("downward_velocity")); // change this to properties file

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, DOWNWARD_VELOCITY);
    entity.move();
  }

}
