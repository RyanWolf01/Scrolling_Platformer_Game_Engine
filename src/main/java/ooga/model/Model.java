package ooga.model;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.containers.EntityContainer;

/**
 * Backend logic is performed in here,
 * methods are called by the controller
 */
public class Model {
  BackendContainer entities;

  public Model(BackendContainer entities){
    this.entities = entities;
  }

  public void step(){

  }

  public void handleMoveKey(MoverAction action){
    entities.mainCharacter().acceptMoveAction(action);
  }

  public void handleAliveKey(AliveAction action){
    entities.mainCharacter().acceptAliveAction(action);
  }

  public void handleCollision(Entity collider, Entity collided){

  }

}
