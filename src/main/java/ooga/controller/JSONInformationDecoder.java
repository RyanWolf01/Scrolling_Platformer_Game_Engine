package ooga.controller;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.entities.data.EntityInfo;
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
   * @param JSONFilePath, string path of the JSON file with the game info
   * @param connectionContainer, a connection container object that will hold all the entities
   * @return connectionContainer, updated, populated connection container of all the initial game entities
   */
  public void makeEntityContainerFromLevelJSON(String JSONFilePath, ConnectionContainer connectionContainer) {
    JSONObject levelJSON = null;
    try {
      levelJSON = initialJSONInformation(JSONFilePath);
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }

    for (Object o : levelJSON.keySet()) {
      if (o == ENTITY_JSON_KEY && checkJSONArrayValue(levelJSON.get(o))) {
        JSONArray jsonEntityArray = (JSONArray) levelJSON.get(o);
        populateEntityList(jsonEntityArray);
        populateConnectionContainer(jsonEntityArray, connectionContainer);
      } else {
        generalInfoMap.put((String) o, levelJSON.get(o));
      }
    }
  }


  // TODO: could also maybe do try catch here instead, lots of logic, longish method
  /**
   * Method to take in the JSONArray of objects for each entity, and the connection container,
   * and populate this connection container properly with the required parameters
   * @param entityArray, array of JSON Objects representing info for each entity
   * @param connectionContainer, connection container to be filled
   */
  private void populateConnectionContainer(JSONArray entityArray, ConnectionContainer connectionContainer) {
    for (Object jsonObject: entityArray) {
      JSONObject singleEntity;
      EntityInfo singleEntityInfo;
      if (checkJSONObjectValue(jsonObject)) {
        singleEntity = (JSONObject) jsonObject;
      } else {
        // TODO: custom exception here
        throw new RuntimeException("Not a JSON object");
      }
      if (!checkRequiredKeys(singleEntity)) {
        // TODO: custom exception here
        throw new RuntimeException("Not all required information for entity");
      }
      singleEntityInfo = makeEntityInfoFromJSONObject(singleEntity);
      connectionContainer.addNewEntity((int) singleEntity.get("x"), (int) singleEntity.get("y"),
          (double) singleEntity.get("height"), (double) singleEntity.get("width"), (String) singleEntity.get("type"),
          singleEntityInfo);
    }
  }

  /**
   * This method takes in a specific JSON array holds information for each of the entities
   * and populates a list of all of the JSON objects, one for each entity in a level JSON file uploaded
   * by the user
   * @param entityArray
   */
  private void populateEntityList(JSONArray entityArray) {
    for (Object jsonObject: entityArray) {
      if (checkJSONObjectValue(jsonObject)) {
        entityJSONList.add((JSONObject) jsonObject);
      } else {
        // TODO: custom exception here
        throw new RuntimeException("Not a json object");
      }
    }
  }

  /**
   * This method creates entity info from a json object, taking all information in key value
   * pairs in a JSON object for a specific entity and translating everything outside the required keys
   * into an entityInfo that is used to add a new entity to the connection container
   * @param entityInformation
   * @return
   */
  private EntityInfo makeEntityInfoFromJSONObject(JSONObject entityInformation) {
    EntityInfo entityInfo = new EntityInfo((String) entityInformation.get("type"));
    for (Object key : entityInformation.keySet()) {
      if (!REQUIRED_ENTITY_PARAMETERS.contains(key)) {
        entityInfo.set((String) key, (String) entityInformation.get(key));
      }
    }
    return entityInfo;
  }


  // TODO: figure this out once I know what we are changing with collision info
  private CollisionData makeCollisionDataFromJSONObject(JSONObject entityInformation) {
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

  // TODO: refactor this method, has to be able to be expressed more simply
  /**
   * Method that checks to ensure that a set of information for an entity has all the required information
   * and keys that are needed to initialize and add entities to a connection container
   * @param entityObject
   * @return boolean representing whether the JSON object has required info
   */
  private boolean checkRequiredKeys(JSONObject entityObject) {
    for (String key : REQUIRED_ENTITY_PARAMETERS) {
      if (!entityObject.containsKey(key)) {
        return false;
      }
    }
    return true;
  }
}
