package ooga.model.actions.maincharacteractions;

import ooga.model.GameState;
import ooga.model.entities.entitymodels.MainCharacter;

public class SetGameStateToUserLost implements MainCharacterAction {

  @Override
  public void execute(MainCharacter mainCharacter) {
    mainCharacter.setGameState(GameState.USER_LOST);
  }
}
