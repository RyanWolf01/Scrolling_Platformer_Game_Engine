package ooga.controller;

import ooga.controller.saveloadhandling.CheckpointDirectory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckpointDirectoryTest {

  public static final String SLASH = System.getProperty("file.separator");
  public static final String TEST_SAMPLE = System.getProperty("user.dir") + SLASH + "src" + SLASH + "test" + SLASH
      + "resources" + SLASH + "testJSONsample" + SLASH;
  JSONInformationDecoder decoder = new JSONInformationDecoder(TEST_SAMPLE + "level.json",
      TEST_SAMPLE + "collisions.json", TEST_SAMPLE + "controls.json");

  @Test
  void testDirectoryCreation() {
    JSONObject level = new JSONObject();
    JSONArray array = new JSONArray();
    level.put("Melon", "Watermelon");
    level.put("Entity", "Melon");
    JSONObject collisions = new JSONObject();
    JSONObject controls = new JSONObject();
  }

}
