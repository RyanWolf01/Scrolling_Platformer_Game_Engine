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

  public void step(){

  }

  public void handleKeyPress(MoverAction action){

  }

  public void handleKeyPress(AliveAction action){

  }

  public void handleCollision(Entity collider, Entity collided){

  }

}
