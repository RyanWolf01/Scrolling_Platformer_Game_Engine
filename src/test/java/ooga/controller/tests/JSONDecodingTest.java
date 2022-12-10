package ooga.controller.tests;

import java.util.ResourceBundle;
import ooga.controller.JSONInformationDecoder;
import org.eclipse.jetty.util.ajax.JSON;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ooga.controller.JSONInformationDecoder;

public class JSONDecodingTest {

  // make some sample JSON array and object here to use to test methods
  JSONArray sampleArray = new JSONArray();
  JSONObject sampleObject = new JSONObject();
  JSONObject bigObject = new JSONObject();

  public static final String TEST_SAMPLE = "testJSONsample";
  JSONInformationDecoder decoder = new JSONInformationDecoder(TEST_SAMPLE + "/level.json",
      TEST_SAMPLE + "/collisions.json", TEST_SAMPLE + "/controls.json");

  @Test
  void testJSONUserControlHandler() {
    sampleObject.put("Canteloupe", 7);
    sampleObject.put("Watermelon", 12);
    sampleObject.put("Honeydew", 5);
    bigObject.put("Melons", sampleArray);
    JSONObject accurateJSON = decoder.initialJSONInformation(SAMPLE_MELON_JSON);
  }

  @Test
  void testJSONCollisionChartMaking() {

  }

  @Test
  void testJSONConnectionContainer() {

  }



}
