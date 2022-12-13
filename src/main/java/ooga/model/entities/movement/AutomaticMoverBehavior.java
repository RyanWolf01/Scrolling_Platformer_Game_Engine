package ooga.model.entities.movement;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.entitymodels.MovingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AutomaticMoverBehavior extends BasicMoverBehavior{

  private final Logger LOG = LogManager.getLogger(AutomaticMoverBehavior.class);
  private MovingEntity thisEntity;
  private final MovementQueue movementQueue;
  private int stepCounter;
  private final int MOVEMENT_RATE;
  private final String MOVEMENT_RATE_KEY;
  ResourceBundle entityInfoResources = ResourceBundle.getBundle("properties/movement");

  /**
   * Automatic Movers have preset moves that are repeated a rate specificed by the user
   * @param movementQueue queue of moves to be automatically made
   * @param movingEntity the entity on which these moves are being made
   */
  public AutomaticMoverBehavior(MovementQueue movementQueue, MovingEntity movingEntity) {
    this.movementQueue = movementQueue;
    this.thisEntity = movingEntity;
    stepCounter = 0;

    ResourceBundle entityInfoProperties = ResourceBundle.getBundle("properties/entityInfo");
    try {
      MOVEMENT_RATE_KEY = entityInfoProperties.getString("movement_rate");
    } catch (NullPointerException exception) {
      throw new MiscellaneousPropertiesException("movement_rate_key_missing");
    }

    int tempRate;
    try {
      tempRate = Integer.parseInt(movingEntity.getImmutableEntityInfo().get(MOVEMENT_RATE_KEY));
    } catch (NullPointerException | NumberFormatException exception) {
      LOG.error("movement rate formatted incorrectly in entity info");
      // try from properties file
      try {
        tempRate = Integer.parseInt(entityInfoResources.getString(MOVEMENT_RATE_KEY));
      } catch (NullPointerException | NumberFormatException propertiesException) {
        throw new MovementDataException(
            "no value provided for automatic movement rate in resources", propertiesException);
      }
    }
    MOVEMENT_RATE = tempRate;
  }

  /**
   * This will move the entity automatically based on the configured MoverActions in the MoveQueue
   */
  @Override
  public void move(){

    try {


      if(shouldAutomaticMove()){
        MoverAction move = movementQueue.nextMove();
        move.execute(thisEntity);
      }

    }
    catch(NullPointerException exception){
      throw new MovementDataException("MovementQueue not initialized or empty.", exception);
    }

    stepCounter++;

  }

  /**
   * helper method to determine if automatic movers should move this step
   */
  private boolean shouldAutomaticMove(){
    if(stepCounter % MOVEMENT_RATE == 0)
      return true;
    return false;
  }

}
