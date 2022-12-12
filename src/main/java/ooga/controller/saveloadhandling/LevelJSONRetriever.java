package ooga.controller.saveloadhandling;

import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.containers.LivingContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

public class LevelJSONRetriever {

  Logger LOG = LogManager.getLogger(LevelJSONRetriever.class);

  public static final String ENTITY_KEY = "Entity";
  /*
  The purpose of this class is to take the backend container, or alive container,
  and be able to remake the level JSON from it to create a checkpoint directory
   */

  /**
   * Format of the level JSON:
   * General, information
   * Information about the entity that will need to be updated is the
   * location (x and y) along with whether or not its alive
   * EntityInfo stuff like lives may also change, so update this
   * Entity: [{entity}, {entity}, {entity}]
   */

  public JSONObject currentLevelJSON;


  public LevelJSONRetriever(BackendContainer backendContainer, LivingContainer livingContainer) {
    this.currentLevelJSON = generateLevelJSON();
  }

  public JSONObject generateLevelJSON() {
    return new JSONObject();
  }



}
