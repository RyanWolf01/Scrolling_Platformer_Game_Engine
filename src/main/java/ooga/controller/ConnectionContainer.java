package ooga.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.Main;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.data.EntityInfo;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;

/**
 * This container holds all the objects for the frontend and backend. There is a mapping between the frontend
 * and backend objects held in here. The initialization calls the addNewEntity() method to add an entity from
 * the JSON to the game.
 */
public class ConnectionContainer {
  public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Entities");
  public static final ResourceBundle containerResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Containers");
  private BackendContainer entities;
  private JSONInformationDecoder decoder;
  private NodeContainer nodes;
  private Map<ScrollingNode, Entity> connectorMap;

  public ConnectionContainer(JSONInformationDecoder decoder){
    entities = new BackendContainer(decoder);
    nodes = new NodeContainer();
    connectorMap = new HashMap<>();
    decoder = this.decoder;
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
    String imageURL = null;
    try{
      imageURL = info.get("texture");
    }
    catch(IllegalArgumentException e){
      // TODO: Fix this
      //imageURL = something from default_images resource file
    }

    Entity newEntity = entities.addNewEntity(xCoordinate, yCoordinate, height, width, type, info);
    ScrollingNode newNode = new ScrollingNode(xCoordinate, yCoordinate, height, width, imageURL);

    nodes.addNode(newNode);
    if(isMainCharacterType(type)){
      nodes.setMainCharacter(newNode);
    }

    connectorMap.put(newNode, newEntity);
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

  public boolean isCollidable(ScrollingNode node){
    return entities.isCollidable(connectorMap.get(node));
  }

  public BackendContainer entities(){
    return entities;
  }

  private boolean isMainCharacterType(String type){
    return Arrays.asList(containerResources.getString("main_characters").split(",")).contains(type);
  }
}
