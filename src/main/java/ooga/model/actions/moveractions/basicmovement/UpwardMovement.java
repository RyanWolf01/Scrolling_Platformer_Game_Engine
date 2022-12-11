package ooga.model.actions.moveractions.basicmovement;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpwardMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(UpwardMovement.class);

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (! entity.canMoveUp()) return;
    entity.changeVelocities(0, entity.getMoverData().getUpwardActionVelocity());
  }

}
