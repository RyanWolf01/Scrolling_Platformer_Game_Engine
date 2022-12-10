package ooga.controller.tests;

import org.junit.jupiter.api.Test;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONDecodingTest {

  // make some sample JSON array and object here to use to test methods
  JSONArray sampleArray = new JSONArray();
  JSONObject sampleObject = new JSONObject();
  JSONObject bigObject = new JSONObject();



  @Test
  void testJSONUserControlHandler() {
    sampleObject.put("Canteloupe", 7);
    sampleObject.put("Watermelon", 12);
    sampleObject.put("Honeydew", 5);
    bigObject.put("Melons", sampleArray);


  }

  @Test
  void testJSONCollisionChartMaking() {

  }

  @Test
  void testJSONConnectionContainer() {

  }



}
