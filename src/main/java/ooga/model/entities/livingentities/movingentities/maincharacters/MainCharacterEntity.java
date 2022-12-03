package ooga.model.entities.livingentities.movingentities.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.livingentities.movingentities.MovingCharacter;
import ooga.model.entities.info.Info;

/**
 * Maybe all main character entities
 */
public abstract class MainCharacterEntity extends MovingCharacter implements UserControllable {

  public MainCharacterEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
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