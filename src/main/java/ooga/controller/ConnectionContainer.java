package ooga.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.Main;
import ooga.controller.exceptions.MalformedJSONException;
import ooga.model.Model;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.movingentities.AutomaticMovingCharacter;
import ooga.view.ViewInfo;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This container holds all the objects for the frontend and backend. There is a mapping between the frontend
 * and backend objects held in here. The initialization calls the addNewEntity() method to add an entity from
 * the JSON to the game.
 */
public class ConnectionContainer {
  private BackendContainer entities;
  private JSONInformationDecoder decoder;
  private NodeContainer nodes;
  private Map<ScrollingNode, Entity> connectorMap;
  private CoordinateTranslator translator;
  private static final Logger LOG = LogManager.getLogger(ConnectionContainer.class);

  public ConnectionContainer(JSONInformationDecoder decoder){
    this.decoder = decoder;
    entities = new BackendContainer(decoder);
    nodes = new NodeContainer();
    connectorMap = new HashMap<>();
    makeCoordinateTranslator();
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
    } catch(IllegalArgumentException e){
      throw new MalformedJSONException("Texture field missing in entity");
    }

    Entity newEntity = entities.addNewEntity(xCoordinate, yCoordinate, height, width, type, info);
    ScrollingNode newNode = new ScrollingNode(xCoordinate, translator.translateY(yCoordinate, height), height, width, imageURL);

    nodes.addNode(newNode);
    if(entities.isMainCharacterType(type)){
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
      node.update(entity.getXCoordinate(), translator.translateY((int) entity.getYCoordinate(), entity.getHeight()));
    }
  }

  public boolean isCollidable(ScrollingNode node){
    return entities.isCollidable(connectorMap.get(node));
  }

  public BackendContainer entities(){
    return entities;
  }

  private void makeCoordinateTranslator(){
    ViewInfo info = decoder.viewInfo();
    this.translator = new CoordinateTranslator(info.cameraHeight(), info.cameraWidth());
  }
}
