package ooga.view;

/**
 * The Camera will handle the operation of deciding when and where the viewable area of the game will scroll when the player moves.
 * It will also hold margin values that allow the player to move freely inside of a box of the viewable area before scrolling.
 * Lastly, the game can control moments when the camera does not scroll (could be end/beginning of level or cutscenes).
 */


public interface Camera {


  /**
   * Updates where the player is currently, and does operations to scroll
   * @param playerX
   * @param playerY
   */
  public void setPlayerLocation(double playerX, double playerY);

  /**
   * Gets the camera origin for purposes of showing/ not showing things in the camera area.
   * @return the origin point of the camera (top-left)
   */
  public double getCameraX();

  public double getCameraY();
  /**
   * Stops the ability to scroll
   */
  public void stopScroll();
  /**
   * Although scrolling is automatically enabled at the start of the class, this method is used to restart scrolling.
   */
  public void startScroll();
}