package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gravity implements MoverAction{

  private static final Logger LOG = LogManager.getLogger(Gravity.class);
  private final double TERMINAL_VELOCITY;

  /**
   * Bounce constructor initializes default Bounce velocity value
   */
  public Gravity(){

    double tempTerminalVelocity;
    try{
      tempTerminalVelocity = Double.parseDouble(
          ResourceBundle.getBundle("properties/movement").getString("terminal_velocity"));
    }
    catch(NumberFormatException exception){
      throw new MovementDataException("incorrect terminal velocity format", exception);
    }
    TERMINAL_VELOCITY = tempTerminalVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (!entity.canMoveDown()) return;

    double gravityVelocity = entity.getMoverData().getGravityVelocity();
    if(entity.getYVelocity() + gravityVelocity <= TERMINAL_VELOCITY)
      entity.changeVelocities(0, gravityVelocity);
  }

}

