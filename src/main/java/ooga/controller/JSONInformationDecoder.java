package ooga.controller;
import static ooga.model.entities.info.ImmutableInfo.COLLIDABLE_TYPE_KEY;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ooga.controller.exceptions.MalformedJSONException;
import ooga.model.actions.moveractions.MoverActionGetter;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.Criteria;
import ooga.model.collisions.collisionhandling.CriteriaDefault;
import ooga.model.collisions.collisionhandling.DefaultCollisionChart;

import ooga.model.entities.movement.MovementQueue;
import ooga.model.entities.info.EntityInfo;
import ooga.view.ViewInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONInformationDecoder {
  private static final Logger LOG = LogManager.getLogger(JSONInformationDecoder.class);

  private String levelJSON;
  private String collisionsJSON;
  private String controlsJSON;
  private static final String ENTITY_JSON_KEY = "Entity";
  private List<JSONObject> entityJSONList;
  private Map<String, Object> generalInfoMap;
  public static final List<String> REQUIRED_ENTITY_PARAMETERS = List.of("type", "x", "y", "width", "height");

  public JSONInformationDecoder(String levelJSON, String collisionsJSON, String controlsJSON){
    this.levelJSON = levelJSON;
    this.controlsJSON = controlsJSON;
    this.collisionsJSON = collisionsJSON;
    this.generalInfoMap = new HashMap<>();
    this.entityJSONList = new ArrayList<>();
  }


  /**
   * This method takes in the JSONFilePath as a string from the file reading where this class lives
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
   * @param connectionContainer, a connection container object that will hold all the entities
   * @return connectionContainer, updated, populated connection container of all the initial game entities
   */
  public void makeEntityContainerFromLevelJSON(ConnectionContainer connectionContainer) {
    JSONObject levelJSONObject = null;
    try {
      levelJSONObject = initialJSONInformation(levelJSON);
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }

    for (Object o : levelJSONObject.keySet()) {
      if (o.equals(ENTITY_JSON_KEY) && checkJSONArrayValue(levelJSONObject.get(o))) {
        JSONArray jsonEntityArray = (JSONArray) levelJSONObject.get(o);
        populateEntityList(jsonEntityArray);
        populateConnectionContainer(jsonEntityArray, connectionContainer);
      } else {
        generalInfoMap.put((String) o, levelJSONObject.get(o));
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
      connectionContainer.addNewEntity(Integer.parseInt((String) singleEntity.get("x")), Integer.parseInt((String) singleEntity.get("y")),
          Double.parseDouble((String) singleEntity.get("height")), Double.parseDouble((String) singleEntity.get("width")), (String) singleEntity.get("type"),
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
        throw new MalformedJSONException("Not a json object");
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
    EntityInfo entityInfo = new EntityInfo((String) entityInformation.get(COLLIDABLE_TYPE_KEY));
    for (Object key : entityInformation.keySet()) {
      if (!REQUIRED_ENTITY_PARAMETERS.contains(key)) {
        entityInfo.set((String) key, (String) entityInformation.get(key));
      }
    }
    return entityInfo;
  }

  public CollisionChart makeCollisionDataFromJSONObject(String type) {
    return makeCollisionDataFromJSONObject(type, new DefaultCollisionChart());
  }


  // TODO: refactor this method to simplify the control flow logic
  /*
  Basically, we should get every single file in the collisions folder and combine it into one big
  JSON object. Then look for the type in here, and fill in type values for when you're checking
  OPPONENT_TYPE?...
   */
  private CollisionChart makeCollisionDataFromJSONObject(String type, CollisionChart collisionChart) {
    JSONObject allJSON;
    JSONArray criteriaListJSON;
    String parent;
//    List<Criteria> criteriaList = new ArrayList<>();
//    CollisionChart defaultCollisionChart = new DefaultCollisionChart();

    // make sure we can open JSON file
    try {
      allJSON = initialJSONInformation(collisionsJSON);
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }

    // make sure this type has a corresponding CollisionChart
    if (! checkJSONObjectValue(allJSON.get(type))) {
      throw new RuntimeException("invalid type");
    }
    JSONObject entityJSON = (JSONObject) allJSON.get(type);
    if (! checkJSONArrayValue(entityJSON.get("collision_chart"))) {
      throw new RuntimeException("invalid type");
    }

    criteriaListJSON = (JSONArray) entityJSON.get("collision_chart");
    parent = (String) entityJSON.get("parent");

    for (Object criteriaJSON : criteriaListJSON) {
      // Items to be loaded into a Criteria object, to be loaded into the criteriaList
      Map<String,String> criteriaMap = new HashMap<>();
      ActionDataContainer actionDataContainer = new ActionDataContainer();

      if (! checkJSONObjectValue(criteriaJSON)) {
        continue;
      }
      JSONObject criteriaElement = (JSONObject) criteriaJSON;

      for (Object j : criteriaElement.keySet()) {
        if (j.equals("ACTIONS")) {
          //TODO: surround with try catch and check type errors
          loadActionDataContainer(actionDataContainer, (JSONArray) criteriaElement.get(j));
        } else {
          criteriaMap.put((String) j, (String) criteriaElement.get(j));
        }
      }

      Criteria criteria = new CriteriaDefault(criteriaMap, actionDataContainer);
      collisionChart.addCriteria(criteria);
    }


    if (allJSON.containsKey(parent)) {
      collisionChart = makeCollisionDataFromJSONObject(parent, collisionChart);
    }
    return collisionChart;
  }


  private void loadActionDataContainer(ActionDataContainer actionDataContainer, JSONArray actionJSONArray) {
    for (Object o : actionJSONArray) {
      JSONObject action;
      Collection<String> parameters;
      if (checkJSONObjectValue(o)) {
        action = (JSONObject) o;

      } else {
        throw new RuntimeException("not an object");
      }

      // TODO: handle exception here**
      parameters = (Collection<String>) action.get("params");
      ActionData actionData = new ActionData((String) action.get("classname"), (String) action.get("action_interface"),
          parameters);
      actionDataContainer.addActionData(actionData);
    }
  }


  /**
   * Method that takes in String for the controls JSON and the UserControlHandler and populates the handler with the
   * key value pairs of controls and actions
   * @param controlHandler, UserControlHandler we want to populate with actions
   * @return populated controlHandler with all controls in it
   */
  public UserControlHandler makeUserControlHandlerFromJSON(UserControlHandler controlHandler) {
    JSONObject controlsJSONObject = null;
    try {
      controlsJSONObject = initialJSONInformation(controlsJSON);
    } catch (IOException | ParseException e) {
      throw new RuntimeException(e);
    }
    for (Object o : controlsJSONObject.keySet()) {
      if (checkJSONArrayValue(controlsJSONObject.get(o))) {
        throw new RuntimeException("Not a valid key value pair for controls");
      } else {
        controlHandler.addControl((String) o, (String) controlsJSONObject.get(o));
      }
    }
    return controlHandler;
  }

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

  /**
   * Returns a MovementQueue of actions from JSON entity with certain types
   * @param type type of the entity for which we must make a MovementQueue
   * @return MovementQueue (read above)
   */
  public MovementQueue getMovementQueue(String type){
    MoverActionGetter moverActionGetter = new MoverActionGetter();
    MovementQueue moves = new MovementQueue();

    JSONObject levelJSONObject = null;
    try {
      levelJSONObject = initialJSONInformation(levelJSON);
    } catch (IOException | ParseException e) {
      throw new MalformedJSONException("Level JSON unreadable");
    }

    JSONArray entityArray;
    try{
      entityArray = (JSONArray) levelJSONObject.get(ENTITY_JSON_KEY);
    }
    catch (RuntimeException e){
      throw new MalformedJSONException("Make sure Entity array in JSON is an array", e);
    }

    try{
      // Down-casting here because the entityArray is a JSONArray that contains JSONObjects
      for(Object entity: entityArray){
        if(((JSONObject) entity).get("type").equals(type)){
          String movementsCSV = (String) ((JSONObject) entity).get("movement_queue");

          for(String move: movementsCSV.split(",")){
            moves.addMove(moverActionGetter.moverActionTranslate(move));
          }

        }
      }
    }
    catch (NullPointerException e){
      LOG.debug("Make sure AutomaticMover of type "+type+" are formatted correctly", e);
    }

    return moves;
  }

  public ViewInfo viewInfo(){
    ViewInfo info;

    JSONObject levelJSONObject = null;
    try {
      levelJSONObject = initialJSONInformation(levelJSON);
    } catch (IOException | ParseException e) {
      throw new MalformedJSONException("Level JSON unreadable");
    }

    String name;
    String backgroundURL;
    int cameraHeight;
    int cameraWidth;
    String style;
    String scrollingDirection;
    try{
      name = (String) levelJSONObject.get("name");
      backgroundURL = (String) levelJSONObject.get("background");
      cameraHeight = Integer.parseInt((String) levelJSONObject.get("camera_height"));
      cameraWidth = Integer.parseInt((String) levelJSONObject.get("camera_width"));
      style = (String) levelJSONObject.get("style");
      scrollingDirection = (String) levelJSONObject.get("scrolling_direction");

    } catch (RuntimeException e){
      throw new MalformedJSONException("level.json missing required key(s)", e);
    }

    info = new ViewInfo(name, backgroundURL, cameraHeight, cameraWidth, style, scrollingDirection);

    return info;
  }

}
