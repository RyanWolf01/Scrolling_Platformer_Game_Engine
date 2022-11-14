package ooga.controller;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.containers.EntityContainer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONInformationDecoder implements JSONTranslator {

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
   * of initial entities given in the use chosen JSON file
   * @param levelJSON, JSONObject representative of the JSON file with the game info
   * @param entityJSON, JSONObject representative of the JSON file for entities
   * @return connectionContainer, connection container of all the initial game entities
   */
  public ConnectionContainer makeEntityContainerFromLevelJSON(JSONObject levelJSON, JSONObject entityJSON) {
    ConnectionContainer connectionContainer = new ConnectionContainer();
    EntityInfo entityInfo = makeEntityInfoFromJSONObject(entityJSON);
    for (Object o : levelJSON.keySet()) {
      if (checkJSONObjectValue(levelJSON.get(o))) {
        JSONObject valueJSONObject = (JSONObject) levelJSON.get(o);
        connectionContainer.addNewEntity((int) valueJSONObject.get("x"), (int) valueJSONObject.get("y"),
            (double) valueJSONObject.get("height"), (double) valueJSONObject.get("width"), (String) valueJSONObject.get("type"),
            entityInfo);
      } else {
        //this is some other general info and can be added to maybe like a general info map?
        //another resources file to use reflection for style and other general info
      }
    }
    return connectionContainer;
  }

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

  // **WHAT DO WE WANT TO DO WITH THIS, WANT TO USE IT IN THE ABOVE METHODS
  // HAVE TO HAVE IT RETURN MAPS AND OTHER INFORMATION WE WANT
  public void handleJSONObjectParsing(JSONObject jsonObject) {
    for (Object o : jsonObject.keySet()) {
      if (checkJSONObjectValue(jsonObject.get(o))) {
        //this is an entity
      } else {
        //this is some other general info and can be added to maybe like a general info map?
        //another resources file to use reflection for style and other general info
      }
    }

  }

  /**
   * This method will take in the value in a key value pair in a JSON Object and determine whether the
   * value is another JSON object, if it is, and this method returns true, then the key it corresponds to is an entity
   * If this is false, the key, value pair in the JSON object is another type of general information
   * @param value, the value in the key value pair in the JSON object
   * @return boolean value that determines whether the key is some kind of entity or not
   */
  private boolean checkJSONObjectValue(Object value) {
    return value instanceof JSONObject;
  }

}
