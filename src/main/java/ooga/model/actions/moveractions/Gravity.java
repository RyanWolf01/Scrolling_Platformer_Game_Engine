package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.Mover;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Gravity implements MoverAction{

  private static final Logger LOG = LogManager.getLogger(Gravity.class);
  private final int GRAVITY_VELOCITY;

  /**
   * Bounce constructor initializes default Bounce velocity value
   */
  public Gravity(){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));
    }
    catch(NumberFormatException exception){
      LOG.error("incorrect velocity format");
      throw exception;
    }
    GRAVITY_VELOCITY = tempVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, GRAVITY_VELOCITY);
  }

}

