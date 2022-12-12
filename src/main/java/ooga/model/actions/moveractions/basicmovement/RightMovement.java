package ooga.model.actions.moveractions.basicmovement;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(RightMovement.class);

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (! entity.canMoveRight()) return;
    if(entity.getXVelocity() < entity.getMoverData().getRightActionVelocity())
      entity.changeVelocities(entity.getMoverData().getRightActionVelocity(), 0);
  }

}
