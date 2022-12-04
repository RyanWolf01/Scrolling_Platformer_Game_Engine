package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpwardMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(UpwardMovement.class);
  private final int UPWARD_VELOCITY;

  /**
   * UpwardMovement constructor initializes default UpwardMovement velocity value
   */
  public UpwardMovement(){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));
    }
    catch(NumberFormatException exception){
      tempVelocity = 5;
      LOG.error("incorrect velocity format");
    }
    UPWARD_VELOCITY = tempVelocity;
  }

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
