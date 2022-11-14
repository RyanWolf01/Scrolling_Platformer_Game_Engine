package ooga.controller;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.containers.EntityContainer;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.ParseException;
import ooga.model.collisions.collision_handling.Criteria;


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
   * This method takes in the JSONObject created when file reading is done and makes the entity
   * list of initial entities given in the use chosen JSON file
   * @param initialGameJSON, JSONObject representative of the JSON file with the game info
   * @return entities, EntityContainer of all the initial game entities
   * @throws IOException
   * @throws ParseException
   */
  public EntityContainer makeEntityContainerFromLevelJSON(JSONObject initialGameJSON) {
    EntityContainer entities = new EntityContainer();
    /*
    have to parse the JSON object in the correct manner, *** check to make sure
    we are going to format everything for level JSONs the way we have in th example to creat this entity container
    the right way with the right characteristics
    using reflection to do this, making like entity info hold everything but
    length width x and y --> so 5 params for the constructor
    go through the keys in lik
     */
    return entities;
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

  public void handleJSONObjectParsing(JSONObject jsonObject) {
    for (Object o : jsonObject.keySet()) {
      Object value = jsonObject.get(o);
      handleJSONObjectValueChecking(value);
    }

  }

  private JSONObject handleJSONObjectValueChecking(Object value) {
    if (value instanceof JSONObject) {
      handleJSONObjectParsing((JSONObject) value);
      return ((JSONObject) value);
    } else {
      return null;
    }
  }

}
