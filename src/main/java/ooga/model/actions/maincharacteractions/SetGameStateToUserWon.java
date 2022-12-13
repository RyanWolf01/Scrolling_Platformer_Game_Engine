package ooga.model.actions.maincharacteractions;

import ooga.model.entities.maincharacter.MainCharacterState;
import ooga.model.entities.entitymodels.BasicMainCharacter;

public class SetGameStateToUserWon implements MainCharacterAction {

  @Override
  public void execute(BasicMainCharacter basicMainCharacter) {
    basicMainCharacter.setMainCharacterState(MainCharacterState.USER_WON);
  }
}
