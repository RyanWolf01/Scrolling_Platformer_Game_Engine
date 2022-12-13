package ooga.controller.tests;

import java.util.HashMap;
import java.util.Map;
import ooga.controller.saveloadhandling.CheckpointDirectory;
import ooga.controller.saveloadhandling.LevelJSONRetriever;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.containers.LivingContainer;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.entitymodels.BasicStaticCharacter;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.info.Info;
import ooga.model.entities.movement.MovementQueue;
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
    Entity sampleEntity = new BasicStaticCharacter(new DefaultCollisionChart(),5, 5, 10, 10, new Info());
    entityContainer.addEntity(sampleEntity);
    LivingContainer livingContainer = new LivingContainer();
    LevelJSONRetriever retriever = new LevelJSONRetriever(infoMap, entityContainer, livingContainer);
    assertEquals(retriever.currentLevelJSON.get("Melon"), "Canteloupe");
    JSONArray actualEntityArray = new JSONArray();
    JSONObject entity1 = new JSONObject();
    entity1.put("x", "5");
    entity1.put("y", "5");
    entity1.put("height", "10.0");
    entity1.put("width", "10.0");
    actualEntityArray.add(entity1);
    assertEquals(retriever.currentLevelJSON.get("Entity"), actualEntityArray);
  }

}
