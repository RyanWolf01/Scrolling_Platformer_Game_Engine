package ooga.controller.saveloadhandling;

import java.util.List;
import java.util.Map;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.containers.LivingContainer;
import ooga.model.entities.entitymodels.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LevelJSONRetriever {

  Logger LOG = LogManager.getLogger(LevelJSONRetriever.class);

  public static final String ENTITY_KEY = "Entity";

  /**
   * Format of the level JSON:
   * General, information
   * Information about the entity that will need to be updated is the
   * location (x and y) along with whether or not its alive
   * EntityInfo stuff like lives may also change, so update this
   * Entity: [{entity}, {entity}, {entity}]
   */

  public JSONObject currentLevelJSON;
  private List<Entity> seenEntities;
  public static final String ENTITY_KEY = "Entity";

  public LevelJSONRetriever(Map<String, Object> infoMap, BackendContainer backendContainer, LivingContainer livingContainer) {
    this.currentLevelJSON = generateLevelJSON(infoMap, backendContainer, livingContainer);
  }

  public JSONObject generateLevelJSON(Map<String, Object> generalInfoMap, BackendContainer backendContainer, LivingContainer livingContainer) {
    currentLevelJSON = new JSONObject();
    for (String key : generalInfoMap.keySet()) {
      currentLevelJSON.put(key, generalInfoMap.get(key));
    }
    JSONArray entityJSONObject = new JSONArray();
    for(Alive liver : livingContainer) {
      Entity entity = (Entity) liver;
      seenEntities.add(entity);
      entity.
    }

    currentLevelJSON.put(ENTITY_KEY, entityJSONObject);
    return currentLevelJSON;
  }



}
