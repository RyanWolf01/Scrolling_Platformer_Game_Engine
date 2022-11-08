/**
 * The MovableGroup class will be an extension of the Group class. The main draw of this class is enabling the scrolling for the camera.
 */

public interface MovableGroup {


/**
*
 * The moveGroup method will move all elements of the group by a value. This will make it appear that the level is scrolling while the player is moving past a certain point on the screen.
 * @param deltaX amount the x coordinate will move by
 * @param deltaY amount the y coordinate will move by
*/
  public void moveGroup(double deltaX, double deltaY);
}