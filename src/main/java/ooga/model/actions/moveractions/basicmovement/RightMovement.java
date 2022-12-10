package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(RightMovement.class);
  private final int RIGHT_VELOCITY;

  /**
   * RightMovement constructor initializes default RightMovement velocity value
   */
  public RightMovement(){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("right_velocity"));
    }
    catch(NumberFormatException exception){
      LOG.error("incorrect velocity format");
      throw exception;
    }
    RIGHT_VELOCITY = tempVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (! entity.canMoveRight()) return;
    entity.changeVelocities(RIGHT_VELOCITY, 0);
    entity.move();
  }

}
