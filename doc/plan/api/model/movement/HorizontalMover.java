/**
 * Subclass of Movement Interface that moves horizontally
 */
public interface HorizontalMover extends Movement{

  /**
   * Move horizontally
   */
  public void changeHorizontalVelocity(int xVelocityChange);

}