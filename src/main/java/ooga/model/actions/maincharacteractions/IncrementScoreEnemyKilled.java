package ooga.model.actions.maincharacteractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousParsingException;
import ooga.model.entities.entitymodels.BasicMainCharacter;

public class IncrementScoreEnemyKilled implements MainCharacterAction {

  private final int AMOUNT_TO_INCREMENT_BY;

  /**
   * increases score of main character
   */
  public IncrementScoreEnemyKilled(){
    int tempAmount;
    try{
      tempAmount = Integer.parseInt(
          ResourceBundle.getBundle("properties/powerups").getString("increment_score_enemy_killed"));
    }
    catch(NumberFormatException exception){
      throw new MiscellaneousParsingException("incorrect multiplier format", exception);
    }
    AMOUNT_TO_INCREMENT_BY = tempAmount;
  }

  /**
   * increment score of main character
   * @param mainCharacter on which action is executing
   */
  @Override
  public void execute(BasicMainCharacter mainCharacter){
    mainCharacter.updateScore(AMOUNT_TO_INCREMENT_BY);
  }

}
