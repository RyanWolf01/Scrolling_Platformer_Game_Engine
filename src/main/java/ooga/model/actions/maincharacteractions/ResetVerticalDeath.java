package ooga.model.actions.maincharacteractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.alive.BasicAliveBehavior;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.maincharacter.BasicMainCharacterBehavior;
import ooga.model.entities.movement.BasicMoverBehavior;

public class ResetVerticalDeath implements MainCharacterAction {

  private final double VERTICAL_OFFSET;

  public ResetVerticalDeath(){
    double tempOffset;
    try{
      tempOffset = Double.parseDouble(
          ResourceBundle.getBundle("properties/powerups").getString("vertical_offset"));
    }
    catch(NumberFormatException exception){
      throw new MovementDataException("incorrect vertical_offset format", exception);
    }
    VERTICAL_OFFSET = tempOffset;
  }

  /**
   * increment score of main character
   * @param mainCharacter on which action is executing
   */
  @Override
  public void execute(BasicMainCharacter mainCharacter){

    if(mainCharacter.hasPowerUp()){
      mainCharacter.setHasPowerUp(false);
      fallFromSky(mainCharacter);
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
    mainCharacter.setMoverBehavior(new BasicMoverBehavior());
    mainCharacter.resetMoverData();
    mainCharacter.setMainCharacterBehavior(new BasicMainCharacterBehavior(mainCharacter.getAliveBehavior()));
  }

  /**
   *
   * @param mainCharacter falls from sky if didn't die
   */
  private void fallFromSky(BasicMainCharacter mainCharacter){
    mainCharacter.setYCoordinate(mainCharacter.getYCoordinate() + VERTICAL_OFFSET);
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
