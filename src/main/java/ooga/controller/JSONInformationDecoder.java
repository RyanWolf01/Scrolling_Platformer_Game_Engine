package ooga.controller;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.containers.EntityContainer;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONInformationDecoder implements JSONTranslator {

  private static final String ENTITY_JSON_KEY = "Entity";
  private List<JSONObject> entityJSONList;
  private Map<String, Object> generalInfoMap;
  public static final List<String> REQUIRED_ENTITY_PARAMETERS = List.of("type", "x", "y", "width", "height");

  /**
   * This method takes in the JSONFilePath as a string from the file reading where this clqss lives
   * and converts the JSON file chosen by the user into a JSON object of initial game information
   * needed by the model and view
   * @param JSONFilePath, string for the filepath to the JSON file selected by the user
   * @return initialGameStates, JSONObject containing information about the game
   * @throws IOException
   * @throws ParseException
   */
  public JSONObject initialJSONInformation(String JSONFilePath) throws IOException, ParseException {
    FileReader infoFile = new FileReader(JSONFilePath);
    JSONObject initialGameStates = (JSONObject) new JSONParser().parse(infoFile);
    return initialGameStates;
  }

  /**
   * This method takes in the JSONObject created when file reading is done and makes the entity list
   * of initial entities given in the use chosen JSON file, in the form of the connection container
   * It also populates a map of general information about the game, which are any key value pairs in the
   * JSON not in the JSON array value of the Entity key
   * @param levelJSON, JSONObject representative of the JSON file with the game info
   * @param connectionContainer, a connection container object that will hold all the entities
   * @return connectionContainer, updated, populated connection container of all the initial game entities
   */
  public ConnectionContainer makeEntityContainerFromLevelJSON(JSONObject levelJSON, ConnectionContainer connectionContainer) {
    for (Object o : levelJSON.keySet()) {
      if (o == ENTITY_JSON_KEY && checkJSONArrayValue(levelJSON.get(o))) {
        JSONArray jsonEntityArray = (JSONArray) levelJSON.get(o);
        populateEntityList(jsonEntityArray);
        populateConnectionContainer(jsonEntityArray, connectionContainer);
      } else {
        generalInfoMap.put((String) o, levelJSON.get(o));
      }
    }
    return connectionContainer;
  }
  // TODO: make sure to do this using the KEY value instead of how it is now
  // if key is entity, go through the value basically and make sure it had all required information
  // this way, it will also hold everything, only 1 JSON file (plus collision stuff) but no separate one
  // for more entity info, just if the key is in required entity info, then everything else is dumped in entity info

  // we will want this to make entity information from a JSONObject
  public EntityInfo makeEntityInfoFromJSONObject(JSONObject entityInformation) {
    return null;
  }

  // we will want this to make collision data using rows of criteria and returning the collision data chart
  public CollisionData makeCollisionDataFromJSONObject(JSONObject entityInformation) {
    return null;
  }

  // method to handle JSON Object, and check is the value is another JSON object
  // adapting from https://www.baeldung.com/jsonobject-iteration
  // just have to do it for JSON.simple instead og just org.JSON

  /**
   * This method will take in the value in a key value pair in a JSON Object and determine whether the
   * value is a JSONArray, if it is, and this method returns true, then the key it corresponds to is a list of
   * entity information sets
   * If this is false, the key, value pair in the JSON object is another type of general information
   * @param value, the value in the key value pair in the JSON object
   * @return boolean value that determines whether the value is a JSONArray or not
   */
  private boolean checkJSONArrayValue(Object value) {
    return value instanceof JSONArray;
  }

  /**
   * This method will take in the value in an item in a JSON Array and determine whether it is
   * a JSONObject, if it is, and this method returns true
   * @param value, the value in the key value pair in the JSON object
   * @return boolean value that determines whether something is a JSONObject or not
   */
  private boolean checkJSONObjectValue(Object value) {
    return value instanceof JSONObject;
  }

  private void populateEntityList(JSONArray entityArray) {
    for (Object jsonObject: entityArray) {
      if (checkJSONObjectValue(jsonObject)) {
        entityJSONList.add((JSONObject) jsonObject);
      } else {
        // TODO: custom exception here
        throw new RuntimeException();
      }
    }
  }

  /*
  TODO: could also maybe do try catch here instead, lots of logic, longish method
  might be smarter to do this
   */
  private void populateConnectionContainer(JSONArray entityArray, ConnectionContainer connectionContainer) {
    for (Object jsonObject: entityArray) {
      JSONObject singleEntity = new JSONObject();
      if (checkJSONObjectValue(jsonObject)) {
        singleEntity = (JSONObject) jsonObject;
      } else {
        // TODO: custom exception here
        throw new RuntimeException();
      }
      checkRequiredKeys(singleEntity);

    }
  }


  private boolean checkRequiredKeys(JSONObject entityObject) {
    for (String key : REQUIRED_ENTITY_PARAMETERS) {
      if (!entityObject.containsKey(key)) {
        return false;
      }
    }
    return true;
  }
}
