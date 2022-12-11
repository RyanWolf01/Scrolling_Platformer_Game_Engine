package ooga.model.entities.containers;

import java.util.Arrays;
import ooga.controller.JSONInformationDecoder;
import ooga.model.Model;
import ooga.model.entities.*;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.entitymodels.BasicStaticCharacter;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.entitymodels.MainCharacter;
import ooga.model.entities.modelcallers.GameEnderCollidableEntity;


/**
 * This mega container holds all the information the backend needs
 */
public class BackendContainer {
  private EntityContainer entities;
  private MoverContainer movers;
  private CollidableContainer collidables;
  private GameEnderContainer gameEnders;
  private LivingContainer livers;
  private MainCharacter mainCharacter;
  private JSONInformationDecoder decoder;
  private EntityFactory factory;


  public BackendContainer(JSONInformationDecoder decoder){
    livers = new LivingContainer();
    entities = new EntityContainer();
    movers = new MoverContainer();
    collidables = new CollidableContainer();
    gameEnders = new GameEnderContainer();
    factory = new EntityFactory(decoder);
    this.decoder = decoder;
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

    if(isMainCharacterType(type)){ // if it's a main character type entity, overwrite the basic newEntity

      mainCharacter = factory.makeMainCharacter(xCoordinate,yCoordinate, height, width, type, info);

      livers.addLiver(mainCharacter);
      newEntity = mainCharacter;
      collidables.addCollidable(mainCharacter); // all main characters are collidable
    }
    else if(isMoverType(type)){
      MovingCharacter newMover = factory.makeMover(xCoordinate,yCoordinate, height, width, type, info);

      movers.addMover(newMover);
      newEntity = newMover;

      if(isCollidableType(type)){ // an automatic mover that is also a collidable
        collidables.addCollidable(newMover);
      }

      if(isAliveType(type)){
        livers.addLiver(newMover);
      }

    }
    else if(isCollidableType(type)){ // only a collidable
      if(isAliveType(type)){
        BasicStaticCharacter newLivingCollidable = factory.makeLivingStaticCharacter(xCoordinate,yCoordinate, height, width, type, info);
        livers.addLiver(newLivingCollidable);
        collidables.addCollidable(newLivingCollidable);
        newEntity = newLivingCollidable;
      }
      else{
        CollidableEntity newCollidable = factory.makeCollidable(xCoordinate,yCoordinate, height, width, type, info);

        collidables.addCollidable(newCollidable);
        newEntity = newCollidable;
      }

    }
    else if (isGameEnderType(type)) {
      GameEnderCollidableEntity newGameEnder = factory.makeGameEnder(xCoordinate, yCoordinate, height, width, type, info);

      gameEnders.addGameEnder(newGameEnder);
      collidables.addCollidable(newGameEnder);
      newEntity = newGameEnder;
    }
    else{
      if(isAliveType(type)){
        BasicStaticCharacter newLivingCharacter = factory.makeLivingStaticCharacter(xCoordinate,yCoordinate, height, width, type, info);
        livers.addLiver(newLivingCharacter);
        newEntity = newLivingCharacter;
      }
      else{
        newEntity = new StaticEntity(xCoordinate,yCoordinate, height, width, info);
      }

    }

    entities.addEntity(newEntity);

    return newEntity;
  }

  public MoverContainer movers(){
    return movers;
  }

  public MainCharacter mainCharacter(){
    return mainCharacter;
  }

  public boolean isCollidable(Entity entity){
    return collidables.contains(entity);
  }

  public CollidableContainer collidables(){
    return collidables;
  }

  public LivingContainer livers(){
    return livers;
  }

  public EntityContainer entities(){
    return entities;
  }


  public GameEnderContainer gameEnders() { return gameEnders; }

  public boolean isMainCharacterType(String type){
    return Arrays.asList(Model.containerResources.getString("main_characters").split(",")).contains(type);
  }

  public boolean isMoverType(String type){
    return Arrays.asList(Model.containerResources.getString("movers").split(",")).contains(type);
  }

  public boolean isCollidableType(String type){
    return Arrays.asList(Model.containerResources.getString("collidables").split(",")).contains(type);
  }

  public boolean isGameEnderType(String type) {
    return Arrays.asList(Model.containerResources.getString("game_enders").split(",")).contains(type);
  }

  public boolean isAliveType(String type){
    return Arrays.asList(Model.containerResources.getString("living").split(",")).contains(type);
  }

}

