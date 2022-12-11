package ooga.model.actions.moveractions.basicmovement;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeftMovement implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(LeftMovement.class);


  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    if (! entity.canMoveLeft()) return;
    entity.changeVelocities(entity.getMoverData().getLeftActionVelocity(), 0);
  }

}
