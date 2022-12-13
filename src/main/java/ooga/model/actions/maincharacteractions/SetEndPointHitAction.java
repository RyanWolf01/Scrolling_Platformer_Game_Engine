package ooga.model.actions.maincharacteractions;

import ooga.model.entities.entitymodels.MainCharacter;

public class SetEndPointHitAction implements MainCharacterAction {

  @Override
  public void execute(MainCharacter mainCharacter) {
    mainCharacter.setEndPointHit(true);
  }
}
