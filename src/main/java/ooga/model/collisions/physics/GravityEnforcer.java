package ooga.model.collisions.physics;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GravityEnforcer {

  private final double GRAVITY_VELOCITY;
  private static final Logger LOG = LogManager.getLogger(GravityEnforcer.class);

  /**
   * Enforces gravity on a mover. Initializes gravity velocity in constructor.
   */
  public GravityEnforcer(){
    double tempGravityVelocity = 0.1;
    try{
      tempGravityVelocity = Double.parseDouble(
          ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));
    } catch(NumberFormatException exception){
      LOG.error("gravity velocity from properties file formatted incorrectly");
    }
    GRAVITY_VELOCITY = tempGravityVelocity;
  }

  /**
   * apply gravity to a Mover
   * @param mover mover
   */
  public void applyGravity(Mover mover){
    if(mover.isInAir())
      mover.changeVelocities(0, GRAVITY_VELOCITY);
  }

}
