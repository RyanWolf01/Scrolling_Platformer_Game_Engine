package ooga.model.actions.aliveactions;

import ooga.model.entities.alive.Alive;

public class Kill implements AliveAction{

  /**
   * decrease life by 1
   * @param entity is an Alive entity
   */
  @Override
  public void execute(Alive entity){
    entity.kill();
  }

}
