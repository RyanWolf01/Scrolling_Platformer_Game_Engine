package ooga.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.controller.exceptions.MalformedJSONException;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.containers.LivingContainer;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.info.EntityInfo;
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
  private static final Logger LOG = LogManager.getLogger(ConnectionContainer.class);
  private BackendContainer entities;
  private JSONInformationDecoder decoder;
  private NodeContainer nodes;
  private Map<ScrollingNode, Entity> connectorMap;
  private Map<Entity, ScrollingNode> reverseConnectorMap;

  public ConnectionContainer(JSONInformationDecoder decoder){
    entities = new BackendContainer(decoder);
    nodes = new NodeContainer();
    connectorMap = new HashMap<>();
    reverseConnectorMap = new HashMap<>();
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
      throw new MalformedJSONException("Malformed 'texture' field", e);
    }

    Entity newEntity = entities.addNewEntity(xCoordinate, yCoordinate, height, width, type, info);
    ScrollingNode newNode = new ScrollingNode(xCoordinate, yCoordinate, height, width, imageURL);

    nodes.addNode(newNode);
    if(entities.isMainCharacterType(type)){
      nodes.setMainCharacter(newNode);
    }

    connectorMap.put(newNode, newEntity);
    reverseConnectorMap.put(newEntity, newNode);
  }

  /**
   * If you want the backend object that is connected to your frontend object
   * @param node frontend object
   * @return backend object
   */
  public Entity getConnectedEntity(ScrollingNode node){
    return connectorMap.get(node);
  }

  public ScrollingNode getConnectedNode(Entity entity){
    return reverseConnectorMap.get(entity);
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
      node.update(entity.getXCoordinate(), entity.getYCoordinate(), entity.getHeight(), entity.getWidth());
    }

    for(Alive liver : entities.livers()){
      if(liver.getLives() <= 0){
        Entity toRemove = (Entity) liver;
        entities.removeEntity(toRemove);

        LOG.info(toRemove.toString());

        nodes.remove(reverseConnectorMap.get(toRemove));
      }
    }
  }

  public boolean isCollidable(ScrollingNode node){
    return entities.isCollidable(connectorMap.get(node));
  }

  public BackendContainer entities(){
    return entities;
  }
}
