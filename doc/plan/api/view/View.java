/**
 * The View Class is the highest level view class for the project. Most of its public methods will be called by the controller.
 */

public interface View {


  public void addScore(int additionalScore);

  public void addLife();
  public void loseLife();
/**
* Allows for the ability to create a new Node element to be showed on the game
 * @param newElement a Node element which is being added to the HUD of the game
 * @param xLocation
 * @param yLocation
*/
  public void addGuiElement(Node newElement, double xLocation, double yLocation);
/**
* Called by controller to show the new level.
 * @param levelData has all data in the level, from enemies to background and level number.
*/
  public void startLevel(LevelDataWrapper levelData);

/**
*
 * @param levelData has all data in the level, from enemies to background and level number.
*/
  public void step(LevelDataWrapper levelData);

  public EntityGroup getEntityGroup();
}