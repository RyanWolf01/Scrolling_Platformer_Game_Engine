package ooga.model.entities.containers;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import ooga.controller.ConnectionContainer;
import ooga.controller.JSONInformationDecoder;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionChartGetter;
import ooga.model.collisions.collisionhandling.DefaultCollisionChartGetter;
import ooga.model.entities.deadmovingentities.AutomaticMovingEntity;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.StaticEntity;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacterEntity;
import ooga.model.entities.containers.exceptions.InvalidTypeException;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.info.Info;
import ooga.model.entities.deadmovingentities.MovementQueue;

/**
 * This mega container holds all the information the backend needs
 */
public class BackendContainer {
  //public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE+"Entities");
  //public static final ResourceBundle containerResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE+"Containers");
  private EntityContainer entities;
  private AutomaticMoverContainer autoMovers;
  private CollidableContainer collidables;
  private MainCharacterEntity mainCharacter;
  private JSONInformationDecoder decoder;

  public BackendContainer(JSONInformationDecoder decoder){
    entities = new EntityContainer();
    autoMovers = new AutomaticMoverContainer();
    collidables = new CollidableContainer();
    this.decoder = decoder;
  }

  public Entity addNewEntity(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){

    Entity newEntity = null;

    if(isMainCharacterType(type)){ // if it's a main character type entity, overwrite the basic newEntity
      CollisionChartGetter collisionChartGetter = new DefaultCollisionChartGetter();
      CollisionChart chart = collisionChartGetter.getCollisionChart(decoder, type);

      MainCharacterEntity main;
      try {
        main = (MainCharacterEntity) Class.forName(ConnectionContainer.entityClassResources.getString(type)).
            getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
            .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      mainCharacter = main;
      newEntity = main;
      collidables.addCollidable(main); // all main characters are collidable
    }
    else if(isAutomaticMoverType(type)){
      CollisionChartGetter collisionChartGetter = new DefaultCollisionChartGetter();
      CollisionChart chart = collisionChartGetter.getCollisionChart(decoder, type);

      // TODO: fix this so that there is a movement queue, not just null

      AutomaticMovingEntity newMover;
      try {
        newMover = (AutomaticMovingEntity) Class.forName(ConnectionContainer.entityClassResources.getString(type)).
            getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class, MovementQueue.class)
            .newInstance(chart, xCoordinate,yCoordinate, height, width, info, null);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new InvalidTypeException("JSON holds invalid type",e);
      }

      autoMovers.addMover(newMover);
      newEntity = newMover;

      if(isCollidableType(type)){ // an automatic mover that is also a collidable
        collidables.addCollidable(newMover);
      }
    }
    else if(isCollidableType(type)){ // only a collidable
      CollisionChartGetter collisionChartGetter = new DefaultCollisionChartGetter();
      CollisionChart chart = collisionChartGetter.getCollisionChart(decoder, type);

      CollidableEntity newCollidable;

      //zz
      try {
        newCollidable = (CollidableEntity) Class.forName(ConnectionContainer.entityClassResources.getString(type)).
            getConstructor(CollisionChart.class, int.class, int.class, double.class, double.class, Info.class)
            .newInstance(chart, xCoordinate,yCoordinate, height, width, info);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new InvalidTypeException("JSON holds invalid type",e);
      }

      collidables.addCollidable(newCollidable);
      newEntity = newCollidable;
    }

    // TODO: This must be fixed below!! If you add an AutomaticMovingEntity this breaks.
    else{
      try {
        newEntity = (StaticEntity) Class.forName(ConnectionContainer.entityClassResources.getString(type)).
            getConstructor(int.class, int.class, double.class, double.class, Info.class)
            .newInstance(xCoordinate,yCoordinate, height, width, info);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new InvalidTypeException("JSON holds invalid type",e);
      }
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

  private boolean isMainCharacterType(String type){
    return Arrays.asList(ConnectionContainer.containerResources.getString("main_characters").split(",")).contains(type);
  }

  private boolean isAutomaticMoverType(String type){
    return Arrays.asList(ConnectionContainer.containerResources.getString("automatic_movers").split(",")).contains(type);
  }

  private boolean isCollidableType(String type){
    return Arrays.asList(ConnectionContainer.containerResources.getString("collidables").split(",")).contains(type);
  }
}
