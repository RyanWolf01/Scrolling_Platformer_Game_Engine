package ooga.controller.saveloadhandling;

import java.util.ArrayList;
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

  private static final Logger LOG = LogManager.getLogger(LevelJSONRetriever.class);

  public static final String ENTITY_KEY = "Entity";

  public JSONObject currentLevelJSON;
  private List<Entity> seenEntities;

  /**
   * Constructor for levelJSONRetriever
   * @param infoMap
   * @param entityContainer
   * @param livingContainer
   */
  public LevelJSONRetriever(Map<String, Object> infoMap, EntityContainer entityContainer, LivingContainer livingContainer) {
    this.currentLevelJSON = generateLevelJSON(infoMap, entityContainer, livingContainer);
  }

  /**
   * Method to generate the level JSON and make it from the livers and all other entities in the game
   * with their current information
   * @param generalInfoMap
   * @param entityContainer
   * @param livingContainer
   * @return JSONObject, of the current level JSON in the game
   */
  public JSONObject generateLevelJSON(Map<String, Object> generalInfoMap, EntityContainer entityContainer, LivingContainer livingContainer) {
    currentLevelJSON = new JSONObject();
    seenEntities = new ArrayList<>();
    for (String key : generalInfoMap.keySet()) {
      currentLevelJSON.put(key, generalInfoMap.get(key));
    }
    JSONArray entityJSONArray = new JSONArray();
    for (Alive liver : livingContainer) {
      JSONObject singleEntity = new JSONObject();
      singleEntity.put("lives", String.valueOf(liver.getLives()));
      Entity entity = (Entity) liver;
      seenEntities.add(entity);
      addEntityKeyValues(singleEntity, entity);
      entityJSONArray.add(singleEntity);
    }

    for (Entity entity : entityContainer) {
      if (!seenEntities.contains(entity)) {
        JSONObject singleEntity = new JSONObject();
        addEntityKeyValues(singleEntity, entity);
        entityJSONArray.add(singleEntity);
      }
    }

    currentLevelJSON.put(ENTITY_KEY, entityJSONArray);

    return currentLevelJSON;
  }

  private void addEntityKeyValues(JSONObject singleEntity, Entity entity) {
    singleEntity.put("x", String.valueOf((int) entity.getXCoordinate()));
    singleEntity.put("y", String.valueOf((int) entity.getYCoordinate()));
    singleEntity.put("height", String.valueOf(entity.getHeight()));
    singleEntity.put("width", String.valueOf(entity.getWidth()));
    Info info = (Info) entity.getImmutableEntityInfo();
    for (String key : info) {
      singleEntity.put(key, String.valueOf(info.get(key)));
    }
  }

}
