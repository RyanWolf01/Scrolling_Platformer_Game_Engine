package ooga.controller.saveloadhandling;

import java.util.List;
import java.util.Map;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.containers.LivingContainer;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.info.Info;
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

  public LevelJSONRetriever(Map<String, Object> infoMap, EntityContainer entityContainer, LivingContainer livingContainer) {
    this.currentLevelJSON = generateLevelJSON(infoMap, entityContainer, livingContainer);
  }

  public JSONObject generateLevelJSON(Map<String, Object> generalInfoMap, EntityContainer entityContainer, LivingContainer livingContainer) {
    currentLevelJSON = new JSONObject();
    for (String key : generalInfoMap.keySet()) {
      currentLevelJSON.put(key, generalInfoMap.get(key));
    }
    JSONArray entityJSONArray = new JSONArray();
    for (Alive liver : livingContainer) {
      JSONObject singleEntity = new JSONObject();
      Entity entity = (Entity) liver;
      // singleEntity.put("type", ???)
      singleEntity.put("x", entity.getXCoordinate());
      singleEntity.put("y", entity.getXCoordinate());
      singleEntity.put("height", entity.getXCoordinate());
      singleEntity.put("width", entity.getXCoordinate());
      Info info = (Info) entity.getImmutableEntityInfo();
      for (String key : info) {
        singleEntity.put(key, info.get(key));
      }
      entityJSONArray.add(singleEntity);
    }

    for (Entity entity : entityContainer) {
      if (!seenEntities.contains(entity)) {
        JSONObject singleEntity = new JSONObject();
        // singleEntity.put("type", ???)
        singleEntity.put("x", entity.getXCoordinate());
        singleEntity.put("y", entity.getXCoordinate());
        singleEntity.put("height", entity.getXCoordinate());
        singleEntity.put("width", entity.getXCoordinate());
        Info info = (Info) entity.getImmutableEntityInfo();
        for (String key : info) {
          singleEntity.put(key, info.get(key));
        }
        entityJSONArray.add(singleEntity);
      }
    }

    currentLevelJSON.put(ENTITY_KEY, entityJSONArray);

    return currentLevelJSON;
  }



}
