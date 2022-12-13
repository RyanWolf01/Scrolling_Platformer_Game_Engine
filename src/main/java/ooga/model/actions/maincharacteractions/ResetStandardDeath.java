package ooga.model.actions.maincharacteractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.alive.BasicAliveBehavior;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.maincharacter.BasicMainCharacterBehavior;
import ooga.model.entities.movement.BasicMoverBehavior;

public class ResetStandardDeath implements MainCharacterAction {

  /**
   * increment score of main character
   * @param mainCharacter on which action is executing
   */
  @Override
  public void execute(BasicMainCharacter mainCharacter){

    if(mainCharacter.hasPowerUp()){
      mainCharacter.setHasPowerUp(false);
    }
    else{
      mainCharacter.kill();
      resetToInitialPosition(mainCharacter);
    }

    resetMainCharacter(mainCharacter);

  }

  /**
   *
   * @param mainCharacter to be reset to original settings
   */
  private void resetMainCharacter(BasicMainCharacter mainCharacter) {
    mainCharacter.resetVelocities(true, true);
    mainCharacter.setHeight(mainCharacter.getInitialAttributes().height());
    mainCharacter.setWidth(mainCharacter.getInitialAttributes().width());
    mainCharacter.setAliveBehavior(new BasicAliveBehavior(mainCharacter.getAliveBehavior()));
    mainCharacter.setMoverBehavior(mainCharacter.getOriginalMoverBehavior());
    mainCharacter.resetMoverData();
    mainCharacter.setMainCharacterBehavior(new BasicMainCharacterBehavior(mainCharacter));
  }

  /**
   *
   * @param mainCharacter resets to initial postiion if died
   */
  private void resetToInitialPosition(BasicMainCharacter mainCharacter){
    mainCharacter.setXCoordinate(mainCharacter.getInitialAttributes().xCoordinate());
    mainCharacter.setYCoordinate(mainCharacter.getInitialAttributes().yCoordinate());
  }

}
