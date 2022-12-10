package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.Mover;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gravity implements MoverAction{

  private static final Logger LOG = LogManager.getLogger(Gravity.class);
  private final double GRAVITY_VELOCITY;
  private final double TERMINAL_VELOCITY;

  /**
   * Bounce constructor initializes default Bounce velocity value
   */
  public Gravity(){

    double tempGravityVelocity;
    try{
      tempGravityVelocity = Double.parseDouble(
          ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));
    }
    catch(NumberFormatException exception){
      LOG.error("incorrect velocity format");
      throw exception;
    }
    GRAVITY_VELOCITY = tempGravityVelocity;

    double tempTerminalVelocity;
    try{
      tempTerminalVelocity = Double.parseDouble(
          ResourceBundle.getBundle("properties/movement").getString("terminal_velocity"));
    }
    catch(NumberFormatException exception){
      LOG.error("incorrect velocity format");
      throw exception;
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
    if(entity.getYVelocity() + GRAVITY_VELOCITY <= TERMINAL_VELOCITY)
      entity.changeVelocities(0, GRAVITY_VELOCITY);
  }

}

