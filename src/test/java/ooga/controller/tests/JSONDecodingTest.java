package ooga.controller.tests;

import java.io.IOException;
import ooga.controller.JSONInformationDecoder;
import ooga.controller.UserControlHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONDecodingTest {

  public static final String SLASH = System.getProperty("file.separator");
  public static final String TEST_SAMPLE = System.getProperty("user.dir") + SLASH + "src" + SLASH + "test" + SLASH
      + "resources" + SLASH + "testJSONsample" + SLASH;
  JSONInformationDecoder decoder = new JSONInformationDecoder(TEST_SAMPLE + "level.json",
      TEST_SAMPLE + "collisions.json", TEST_SAMPLE + "controls.json");

  @Test
  void testStringToJSONObject() {
    JSONObject sampleControls = new JSONObject();
    sampleControls.put("W", "move_up");
    sampleControls.put("A", "move_left");
    sampleControls.put("D", "move_right");
    sampleControls.put("S", "move_down");
    String controlsPath = System.getProperty("user.dir") + SLASH + "src" + SLASH + "test" + SLASH +
        "resources" + SLASH + "testJSONsample" + SLASH + "controls.json";
    try {
      JSONObject actualControls = decoder.initialJSONInformation(controlsPath);
      assertEquals(sampleControls, actualControls);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testJSONUserControlHandler() {
    UserControlHandler realUserControls = new UserControlHandler();
    realUserControls.addControl("W", "move_up");
    realUserControls.addControl("A", "move_left");
    realUserControls.addControl("D", "move_right");
    realUserControls.addControl("S", "move_down");
    UserControlHandler sampleUserControls = new UserControlHandler();
    sampleUserControls = decoder.makeUserControlHandlerFromJSON(sampleUserControls);
    assertEquals(sampleUserControls, realUserControls);
  }

  @Test
  void testJSONCollisionChartMaking() {

  }

  @Test
  void testJSONConnectionContainer() {

  }



}
