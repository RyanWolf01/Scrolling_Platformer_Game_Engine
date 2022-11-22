package ooga.model.entities.containers;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.ResourceBundle;
import ooga.Main;
import ooga.model.entities.AutomaticMovingEntity;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.Entity;
import ooga.model.entities.characters.maincharacters.MainCharacterEntity;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.movement.MovementQueue;

/**
 * This mega container holds all the information the backend needs
 */
public class BackendContainer {
  public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE+"Entities");
  public static final ResourceBundle containerResources = ResourceBundle.getBundle(
      Main.DEFAULT_RESOURCE_PACKAGE+"Containers");
  private EntityContainer entities;
  private AutomaticMoverContainer autoMovers;
  private CollidableContainer collidables;
  private MainCharacterEntity mainCharacter;

  public BackendContainer(){
    entities = new EntityContainer();
    autoMovers = new AutomaticMoverContainer();
    collidables = new CollidableContainer();
  }

  public Entity addNewEntity(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
    Entity newEntity = null;
    if(isMainCharacterType(type)){ // if it's a main character type entity, overwrite the basic newEntity
      MainCharacterEntity main;
      try {
        main = (MainCharacterEntity) Class.forName(entityClassResources.getString(type)).
            getConstructor(Integer.class, Integer.class, Double.class, Double.class, EntityInfo.class)
            .newInstance(xCoordinate,yCoordinate, height, width, info);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      mainCharacter = main;
      newEntity = main;
      collidables.addCollidable(main); // all main characters are collidable
    }
    else if(isAutomaticMoverType(type)){
      // TODO: fix this so that there is a movement queue, not just null

      AutomaticMovingEntity newMover;
      try {
        newMover = (AutomaticMovingEntity) Class.forName(entityClassResources.getString(type)).
            getConstructor(Integer.class, Integer.class, Double.class, Double.class, EntityInfo.class, MovementQueue.class)
            .newInstance(xCoordinate,yCoordinate, height, width, info, null);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }

      autoMovers.addMover(newMover);
      newEntity = newMover;

      if(isCollidableType(type)){ // an automatic mover that is also a collidable
        collidables.addCollidable(newMover);
      }
    }
    else if(isCollidableType(type)){ // only a collidable
      CollidableEntity newCollidable;

      try {
        newCollidable = (CollidableEntity) Class.forName(entityClassResources.getString(type)).
            getConstructor(Integer.class, Integer.class, Double.class, Double.class, EntityInfo.class)
            .newInstance(xCoordinate,yCoordinate, height, width, info);
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
               InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }

      collidables.addCollidable(newCollidable);
      newEntity = newCollidable;
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

  private boolean isMainCharacterType(String type){
    return Arrays.asList(containerResources.getStringArray("main_characters")).contains(type);
  }

  private boolean isAutomaticMoverType(String type){
    return Arrays.asList(containerResources.getStringArray("automatic_movers")).contains(type);
  }

  private boolean isCollidableType(String type){
    return Arrays.asList(containerResources.getStringArray("collidables")).contains(type);
  }
}
