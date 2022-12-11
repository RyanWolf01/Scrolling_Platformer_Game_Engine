package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.Mover;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bounce implements MoverAction{

  private static final Logger LOG = LogManager.getLogger(Bounce.class);
  private final int BOUNCE_VELOCITY;

  /**
   * Bounce constructor initializes default Bounce velocity value
   */
  public Bounce(){

    int tempBounceVelocity;
    try{
      tempBounceVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));
    }
    catch(NumberFormatException exception){
      LOG.error("incorrect velocity format");
      throw exception;
    }
    BOUNCE_VELOCITY = tempBounceVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(0, BOUNCE_VELOCITY);
  }

}
