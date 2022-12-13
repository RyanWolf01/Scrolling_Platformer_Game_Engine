package ooga.model.actions.moveractions.moveout;

import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeftwardMoveOut implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(UpwardMoveOut.class);

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    entity.changeVelocities(entity.getMoverData().getRightActionVelocity() * -2, 0);
  }

}
