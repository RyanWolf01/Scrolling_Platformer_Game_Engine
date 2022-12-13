package ooga.model.actions.maincharacteractions;

import ooga.model.MainCharacterState;
import ooga.model.entities.entitymodels.BasicMainCharacter;

public class SetGameStateToUserWon implements MainCharacterAction {

  @Override
  public void execute(BasicMainCharacter basicMainCharacter) {
    basicMainCharacter.setGameState(MainCharacterState.USER_WON);
  }
}
