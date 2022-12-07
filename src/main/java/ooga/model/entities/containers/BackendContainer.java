package ooga.model.entities.containers;

import java.util.Arrays;
import ooga.controller.JSONInformationDecoder;
import ooga.model.Model;
import ooga.model.entities.*;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.BasicStaticCharacter;
import ooga.model.entities.livingentities.movingentities.AutomaticMovingCharacter;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacterEntity;


/**
 * This mega container holds all the information the backend needs
 */
public class BackendContainer {
  private EntityContainer entities;
  private AutomaticMoverContainer autoMovers;
  private CollidableContainer collidables;
  private MainCharacterEntity mainCharacter;
  private JSONInformationDecoder decoder;
  private BasicStaticCharacter endGoal;
  private EntityFactory factory;

  // TODO: Add EndGoalEntity

  public BackendContainer(JSONInformationDecoder decoder){
    entities = new EntityContainer();
    autoMovers = new AutomaticMoverContainer();
    collidables = new CollidableContainer();
    factory = new EntityFactory(decoder);
    this.decoder = decoder;
  }

  public boolean wonGame(){
    return endGoal.getLives() == 0;
  }

  /**
   * This method uses if-else logic to determine what kind of Entity this will be based on the type.
   * The reason I think this is valid is that it is very limited and the if tree will not be extended.
   * The cleanest way to sort the new Entities into their appropriate containers is by checking which
   * list their 'type' field is in, and multiple if-else statements will have to be used to perform this action.
   * @return Entity that is described by the parameters
   */
  public Entity addNewEntity(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){

    Entity newEntity = null;

    if(isEndGoalType(type)){
      endGoal = factory.makeLivingStaticCharacter(xCoordinate,yCoordinate, height, width, type, info);

      // TODO: add to living container
    }
    else if(isMainCharacterType(type)){ // if it's a main character type entity, overwrite the basic newEntity

      mainCharacter = factory.makeMainCharacter(xCoordinate,yCoordinate, height, width, type, info);

      newEntity = mainCharacter;
      collidables.addCollidable(mainCharacter); // all main characters are collidable

      // TODO: add to living container
    }
    else if(isAutomaticMoverType(type)){
      AutomaticMovingCharacter newMover = factory.makeAutomaticMover(xCoordinate,yCoordinate, height, width, type, info);

      autoMovers.addMover(newMover);
      newEntity = newMover;

      if(isCollidableType(type)){ // an automatic mover that is also a collidable
        collidables.addCollidable(newMover);
      }
      // TODO: if(isLivingType)
    }
    else if(isCollidableType(type)){ // only a collidable

      CollidableEntity newCollidable = factory.makeCollidable(xCoordinate,yCoordinate, height, width, type, info);

      collidables.addCollidable(newCollidable);
      newEntity = newCollidable;
    }
    else{
      newEntity = new StaticEntity(xCoordinate,yCoordinate, height, width, info);
    }

    entities.addEntity(newEntity);

    return newEntity;
  }

  public AutomaticMoverContainer automaticMovers(){
    return autoMovers;
  }

  public MainCharacterEntity mainCharacter(){
    return mainCharacter;
  }

  public boolean isCollidable(Entity entity){
    return collidables.contains(entity);
  }

  public CollidableContainer collidables(){
    return collidables;
  }

  public boolean isMainCharacterType(String type){
    return Arrays.asList(Model.containerResources.getString("main_characters").split(",")).contains(type);
  }

  public boolean isAutomaticMoverType(String type){
    return Arrays.asList(Model.containerResources.getString("automatic_movers").split(",")).contains(type);
  }

  public boolean isCollidableType(String type){
    return Arrays.asList(Model.containerResources.getString("collidables").split(",")).contains(type);
  }

  public boolean isEndGoalType(String type){
    return Arrays.asList(Model.containerResources.getString("end_goals").split(",")).contains(type);
  }
}
