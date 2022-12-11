package ooga.model.actions.maincharacteractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.model.entities.entitymodels.MainCharacter;

public class IncreaseSizePowerUp implements MainCharacterAction{

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
  public void execute(MainCharacter entity){
    entity.setHeight(entity.getHeight() * SIZE_MULTIPLIER);
    entity.setWidth(entity.getWidth() * SIZE_MULTIPLIER);
  }

}
