package ooga.model.actions.maincharacteractions;

import ooga.model.entities.entitymodels.BasicMainCharacter;

public class SetHasPowerUpTrue implements MainCharacterAction{

  /**
   * set has power up to true
   * @param mainCharacter on which action is executing
   */
  @Override
  public void execute(BasicMainCharacter mainCharacter){
    mainCharacter.setHasPowerUp(true);
  }

}
