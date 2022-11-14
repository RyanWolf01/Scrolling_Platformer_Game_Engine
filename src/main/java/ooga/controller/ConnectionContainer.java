package ooga.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;
import ooga.model.entities.Entity;
import ooga.model.entities.containers.EntityContainer;
import ooga.view.nodes.NodeContainer;
import ooga.view.nodes.ScrollingNode;

/**
 * This container holds all the objects for the frontend and backend. There is a mapping between the frontend
 * and backend objects held in here. The initialization calls the addNewEntity() method to add an entity from
 * the JSON to the game.
 */
public class ConnectionContainer {
  private EntityContainer entities;
  private NodeContainer nodes;
  private Map<ScrollingNode, Entity> connectorMap;

  public ConnectionContainer(){
    entities = new EntityContainer();
    nodes = new NodeContainer();
    connectorMap = new HashMap<>();
  }

  public void addNewEntity(){

  }

}
