package ooga.model.entities.livingentities.movingentities.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.livingentities.movingentities.AutomaticMovingCharacter;
import ooga.model.entities.livingentities.movingentities.MovingCharacter;
import ooga.model.entities.info.Info;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Maybe all main character entities
 */
public abstract class MainCharacterEntity extends MovingCharacter implements UserControllable {

  private static final Logger LOG = LogManager.getLogger(MainCharacterEntity.class);
  public MainCharacterEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height,
      double width, Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  @Override
  public void acceptMoveAction(MoverAction action) {
    try {
      action.execute(this);
    }catch(NullPointerException exception){
      LOG.error("MoverAction is null");
      throw exception;
    }
  }

  @Override
  public void acceptAliveAction(AliveAction action) {
    try {
      action.execute(this);
    }catch(NullPointerException exception){
      LOG.error("AliveAction is null");
      throw exception;
    }
  }
}
