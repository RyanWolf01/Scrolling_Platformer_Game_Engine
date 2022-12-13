package ooga.controller.tests;

import java.util.HashMap;
import java.util.Map;
import ooga.controller.saveloadhandling.CheckpointDirectory;
import ooga.controller.saveloadhandling.LevelJSONRetriever;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.containers.LivingContainer;
import ooga.model.entities.entitymodels.Entity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelJSONRetrieverTest {

  @Test
  void testGenerationSimple() {
    Map<String, Object> infoMap = new HashMap<>();
    infoMap.put("Melon", "Canteloupe");
    infoMap.put("Fruit", "Kiwi");
    EntityContainer entityContainer = new EntityContainer();
    LivingContainer livingContainer = new LivingContainer();
    LevelJSONRetriever retriever = new LevelJSONRetriever(infoMap, entityContainer, livingContainer);
    assertEquals(retriever.currentLevelJSON.get("Melon"), "Canteloupe");
    assertNotEquals(retriever.currentLevelJSON.get("Fruit"), "Raspberry");
  }

  @Test
  void testGenerationComplex() {
    Map<String, Object> infoMap = new HashMap<>();
    infoMap.put("Melon", "Canteloupe");
    infoMap.put("Fruit", "Kiwi");
    EntityContainer entityContainer = new EntityContainer();

    LivingContainer livingContainer = new LivingContainer();
    LevelJSONRetriever retriever = new LevelJSONRetriever(infoMap, entityContainer, livingContainer);
    assertEquals(retriever.currentLevelJSON.get("Melon"), "Canteloupe");
    assertEquals(retriever.currentLevelJSON.get("Fruit"), "Kiwi");
  }

}
