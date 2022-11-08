/**
 * API OVERVIEW DOCUMENT
 * This interface/API will serve as the center of the program to handle input
 * JSON files translate them to formats usable by the frontend and view to set up
 * the initial configuration of the game and save all of the entities in the right locations.
 * In addition, this API will serve to save the initial EntityList of all of the entities in the game
 * to be passed to the backend/model
 */

public interface JSONTranslater {

  /**
   * This method will create the EntityList for the game from the JSON file provided by the user with
   * all of the game information and types of entities held within the game
   * Exceptions handled will include File Note Found exceptions and file formatting exceptions, to be handled
   * via a custom exception handler with custom exceptions and error messages from proprties files in
   * the game.
   *
   * @param JSONPath, the string name for the path to the JSON file used to laod game info
   * @return The immutable EntityList of all the entities in the given game
   * @throws FileNoteFoundException
   * @throws FileFormatException
   */
  public EntityList makeEntityListFromJSON(String JSONPath) throws FileNoteFoundException, FileFormatException;

  /**
   * The above could also just be a List instead of an EntityList, if the construction
   * of the EntityList class were to include it extending List and implementing EntityList such
   * that an EntityList would just be our project specific implementation of a list for our purposes.
   * This would make our API more flexible and general and able to applied in different situations
   * outside of just the context of this project
   */


  /**
   * This will translate the JSON file chosen into a JSON object in case the user makes any changes to anything and would like to
   * save them as JSON, potentially for later use, the project will then contain the information
   * in the JSON file in the form of a JSON object to be used when needed
   *
   * @param JSONPath
   * @return JSON object
   * @throws FileNoteFoundException
   */
  public JSONObject initialJSONInformation(String JSONPath) throws FileNoteFoundException;


  /**
   * This method will cooperate with the view and the frontend to make the new level
   * view, going through the JSON file and initializing all of the elements where they belong in the level, and then
   * calling startLevel to render and show the level on the UI
   */
  public void makeLevelView(JSONObject LevelInfo);

  /**
   * This method can take the updated JSONObject and then perform the necessary steps that we plan to implement and
   * tie in with the functionality of potentially another interface to save the JSON information at the time to the database.
   * This saved state/game will be the updated JSON object file in the program that has been modified
   */
  public void saveCurrentLevelState();


}