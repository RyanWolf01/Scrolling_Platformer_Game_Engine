package ooga.model.entities.characters.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.UserControllable;
import ooga.model.entities.characters.MovingCharacter;
import ooga.model.entities.data.Info;

/**
 * Maybe all main character entities
 */
public abstract class MainCharacterEntity extends MovingCharacter implements UserControllable {

  public MainCharacterEntity(int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  @Override
  public void acceptMoveAction(MoverAction action) {
    action.execute(this);
  }

  @Override
  public void acceptAliveAction(AliveAction action) {
    action.execute(this);
  }
}
