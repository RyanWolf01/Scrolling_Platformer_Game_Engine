package ooga.model.actions.moveractions.basicmovement;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DownwardMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(DownwardMovement.class);
  private final int DOWNWARD_VELOCITY;

  /**
   * DownwardMovement constructor initializes default DownwardMovement velocity value
   */
  public DownwardMovement(){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("downward_velocity"));
    }
    catch(NumberFormatException exception){
      tempVelocity = -5;
      LOG.error("incorrect velocity format");
    }
    DOWNWARD_VELOCITY = tempVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (! entity.canMoveDown()) return;
    entity.changeVelocities(0, DOWNWARD_VELOCITY);
    entity.move();
  }

}
