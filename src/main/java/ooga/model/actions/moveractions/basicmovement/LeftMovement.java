package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;

public class LeftMovement implements MoverAction {

  private static final int LEFT_VELOCITY = Integer.parseInt(
      ResourceBundle.getBundle("properties/movement").getString("left_velocity"));

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(LEFT_VELOCITY, 0);
    entity.move();
  }

}
