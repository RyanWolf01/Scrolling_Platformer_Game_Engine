package ooga.model.collisions.physics;

import java.util.ResourceBundle;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.deadmovingentities.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GravityEnforcer {
  private static final Logger LOG = LogManager.getLogger(GravityEnforcer.class);
  private final double GRAVITY_VELOCITY;
  private final BackendContainer entityContainer;

  /**
   * Enforces gravity on a mover. Initializes gravity velocity in constructor.
   */
  public GravityEnforcer(BackendContainer entityContainer){

    this.entityContainer = entityContainer;

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
   * apply gravity to all movers
   */
  public void applyGravityToAllMovers(){
    for(Mover mover: entityContainer.automaticMovers()){
      applyGravity(mover);
    }
    applyGravity(entityContainer.mainCharacter());
  }

  /**
   * apply gravity to single mover
   */
  private void applyGravity(Mover mover){
    if(mover.isInAir())
      mover.changeVelocities(0, GRAVITY_VELOCITY);
  }

}