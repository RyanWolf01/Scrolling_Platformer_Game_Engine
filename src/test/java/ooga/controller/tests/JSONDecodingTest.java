package ooga.controller.tests;

import java.io.IOException;
import ooga.controller.JSONInformationDecoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONDecodingTest {

  public static final String TEST_SAMPLE = "testJSONsample";
  JSONInformationDecoder decoder = new JSONInformationDecoder(TEST_SAMPLE + "/level.json",
      TEST_SAMPLE + "/collisions.json", TEST_SAMPLE + "/controls.json");

  @Test
  void testStringToJSONObject() {
    JSONObject sampleControls = new JSONObject();
    sampleControls.put("W", "move_up");
    sampleControls.put("A", "move_left");
    sampleControls.put("D", "move_right");
    sampleControls.put("S", "move_down");
    try {
      JSONObject actualControls = decoder.initialJSONInformation(TEST_SAMPLE + "/controls.json");
      assertEquals(sampleControls, actualControls);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  void testJSONUserControlHandler() {
    return;
  }

  @Test
  void testJSONCollisionChartMaking() {

  }

  @Test
  void testJSONConnectionContainer() {

  }



}
