package ooga.model.actions.basicentityactions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.model.entities.entitymodels.Entity;

public class IncreaseSizePowerUp implements BasicEntityAction {

  private final double SIZE_MULTIPLIER;

  /**
   * increases size of main character
   */
  public IncreaseSizePowerUp(){
    double tempMultiplier;
    try{
      tempMultiplier = Double.parseDouble(
          ResourceBundle.getBundle("properties/powerups").getString("size_multiplier"));
    }
    catch(NumberFormatException exception){
      throw new MiscellaneousPropertiesException("incorrect multiplier format", exception);
    }
    SIZE_MULTIPLIER = tempMultiplier;
  }

  /**
   * executes action on entity
   * @param entity on which action is executing
   */
  @Override
  public void execute(Entity entity){
    entity.setHeight(entity.getHeight() * SIZE_MULTIPLIER);
    entity.setWidth(entity.getWidth() * SIZE_MULTIPLIER);
  }

}
