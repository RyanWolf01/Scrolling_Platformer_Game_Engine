package ooga.model.collisions.physics;

import ooga.model.actions.moveractions.Gravity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GravityEnforcer {
  private static final Logger LOG = LogManager.getLogger(GravityEnforcer.class);
  private final BackendContainer entityContainer;
  Gravity gravity;

  /**
   * Enforces gravity on a mover.
   */
  public GravityEnforcer(BackendContainer entityContainer){
    this.entityContainer = entityContainer;
    this.gravity = new Gravity();
  }

  /**
   * apply gravity to all movers
   */
  public void applyGravityToAllMovers(){
    for(Mover mover: entityContainer.movers()){
      applyGravity(mover);
    }
    applyGravity(entityContainer.mainCharacter());
  }

  /**
   * apply gravity to single mover
   */
  private void applyGravity(Mover mover){
    if(mover.isInAir()){
      gravity.execute(mover);
    }
  }

}
