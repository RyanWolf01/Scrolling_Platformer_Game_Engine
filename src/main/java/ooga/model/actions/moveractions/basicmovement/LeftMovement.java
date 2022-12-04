package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeftMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(LeftMovement.class);
  private final int LEFT_VELOCITY;

  /**
   * LeftMovement constructor initializes default LeftMovement velocity value
   */
  public LeftMovement(){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("left_velocity"));
    }
    catch(NumberFormatException exception){
      tempVelocity = -5;
      LOG.error("incorrect velocity format");
    }
    LEFT_VELOCITY = tempVelocity;
  }

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
