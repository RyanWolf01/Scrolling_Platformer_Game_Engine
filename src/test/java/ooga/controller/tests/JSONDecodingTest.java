package ooga.controller.tests;

import java.io.IOException;
import ooga.controller.ConnectionContainer;
import ooga.controller.JSONInformationDecoder;
import ooga.controller.UserControlHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.input.KeyCode;

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
  void testJSONUserControlHandlerSetup() {
    UserControlHandler realUserControls = new UserControlHandler();
    realUserControls.addControl("W", "move_up");
    realUserControls.addControl("A", "move_left");
    realUserControls.addControl("D", "move_right");
    realUserControls.addControl("S", "move_down");
    UserControlHandler sampleUserControls = new UserControlHandler();
    sampleUserControls = decoder.makeUserControlHandlerFromJSON(sampleUserControls);
    assertEquals(realUserControls.isMoveAction(KeyCode.W), sampleUserControls.isMoveAction(KeyCode.W));
    assertEquals(realUserControls.isMoveAction(KeyCode.A), sampleUserControls.isMoveAction(KeyCode.A));
    assertEquals(realUserControls.isMoveAction(KeyCode.D), sampleUserControls.isMoveAction(KeyCode.D));
    assertEquals(realUserControls.isMoveAction(KeyCode.S), sampleUserControls.isMoveAction(KeyCode.S));
  }

  @Test
  void testIncorrectUserControlHandler() {
    UserControlHandler userControls = new UserControlHandler();
    userControls = decoder.makeUserControlHandlerFromJSON(userControls);
    assertFalse()

  }

  @Test
  void testJSONCollisionChartMaking() {

  }

  @Test
  void testJSONConnectionContainer() {

  }





}
