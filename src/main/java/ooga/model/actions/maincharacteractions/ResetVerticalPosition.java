package ooga.model.actions.maincharacteractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.model.entities.entitymodels.BasicMainCharacter;

public class ResetVerticalPosition implements MainCharacterAction {

  /**
   * increment score of main character
   * @param mainCharacter on which action is executing
   */
  @Override
  public void execute(BasicMainCharacter mainCharacter){
    mainCharacter.setYCoordinate(-200);
  }

}
