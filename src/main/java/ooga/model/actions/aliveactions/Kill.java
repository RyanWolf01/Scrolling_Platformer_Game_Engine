package ooga.model.actions.aliveactions;

import ooga.model.entities.deadmovingentities.MovingEntity;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Kill implements AliveAction {

  private static final Logger LOG = LogManager.getLogger(Kill.class);

  /**
   * decrease life by 1
   * @param entity is an Alive entity
   */
  @Override
  public void execute(Alive entity){
    entity.kill();
    LOG.debug(entity.getLives());
  }

}
