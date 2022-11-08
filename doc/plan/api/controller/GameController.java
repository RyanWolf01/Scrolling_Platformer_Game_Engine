/**
 * API OVERVIEW DOCUMENT
 * This API will serve as the brain of the program and the connection between the view of the
 * program and the model of the program. It will contain methods and functionality to
 * communicate changes to the view from the model that need to be reflected to the user on the
 * UI that appears. In addition, it provides functionality to adjust the model and change locations
 * of the main character/player, this interface and API will cooperate with the LevelDataWrapper API.
 */

public interface GameController {

  /**
   * This method will send updated locations from the model to the view when the main character location is altered in
   * the model, which will allow the view to update the location on the UI based on the new absolute x and y coordinate values
   * @param levelData the package of level information needed to check the updated
   * @return a List of integers (really just 2) for the new x and y coordinates of the main character
   */
  public List<Integer> sendLocationUpdateToView(LevelDataWrapper levelData);

  /**
   * This method will send collision updates to the frontend so that the necessary game entities
   * can be removed from the screen if necessary, and in addition the levelData can be updated to reflect which
   * entities have been killed or altered or potentially change the location of various entities as well
   * @param levelData, the levelData of the just current of the game being played, with all the necessary information
   */
  public void sendCollisionUpdateToView(LevelDataWrapper levelData);

  /**
   * This method will update the model location of the main character when the user interacts with the UI and changes
   * their location on the screen, so that the model can check for any necessary collisions and other events that may have occured as a result and report these changes
   * back through the controller to the view to change the UI
   * @param levelData, package of level information, for which the location of the main character must be updated for the model to use
   */
  public void updateCharacterLocation(LevelDataWrapper levelData)''

}