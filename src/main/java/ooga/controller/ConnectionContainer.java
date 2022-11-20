package ooga.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.Main;
import ooga.model.entities.Entity;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.containers.EntityContainer;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;

/**
 * This container holds all the objects for the frontend and backend. There is a mapping between the frontend
 * and backend objects held in here. The initialization calls the addNewEntity() method to add an entity from
 * the JSON to the game.
 */
public class ConnectionContainer {
  public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE+"Entities");
  private EntityContainer entities;
  private NodeContainer nodes;
  private Map<ScrollingNode, Entity> connectorMap;

  public ConnectionContainer(){
    entities = new EntityContainer();
    nodes = new NodeContainer();
    connectorMap = new HashMap<>();
  }

  /**
   * Creates a new backend entity and frontend ScrollingNode, adds these objects to the respective
   * containers and the Map
   * @param xCoordinate intitial X coord
   * @param yCoordinate initial Y Coord
   * @param height height
   * @param width width
   * @param type type defines what class is going to be made in the backend
   * @param info extra info that might be helpful
   */
  public void addNewEntity(int xCoordinate, int yCoordinate, double height, double width, String type, EntityInfo info){
    Entity newEntity;
    try {
      Class<?> clazz = Class.forName(entityClassResources.getString(type));
      newEntity = (Entity) clazz.getConstructor(Integer.class, Integer.class, Double.class, Double.class, EntityInfo.class)
              .newInstance(xCoordinate,yCoordinate, height, width, info);
    } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
             InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }

    String imageURL = null;
    try{
      imageURL = info.get("texture");
    }
    catch(IllegalArgumentException e){
      // TODO: Fix this
      //imageURL = something from default_images resource file
    }

    ScrollingNode node = new ScrollingNode(xCoordinate, yCoordinate, height, width, imageURL);

    entities.addEntity(newEntity);
    nodes.addNode(node);

    connectorMap.put(node, newEntity);
  }

  /**
   * If you want the backend object that is connected to your frontend object
   * @param node frontend object
   * @return backend object
   */
  public Entity getConnectedEntity(ScrollingNode node){
    return connectorMap.get(node);
  }

  /**
   * Returns the viewable objects
   * @return NodeContainer of all the things
   */
  public NodeContainer viewables(){
    return nodes;
  }

  /**
   * Update the view objects' location in case they moved
   */
  public void update(){
    for(ScrollingNode node : connectorMap.keySet()){
      Entity entity = connectorMap.get(node);
      node.update(entity.getXCoordinate(), entity.getYCoordinate());
    }
  }

}
