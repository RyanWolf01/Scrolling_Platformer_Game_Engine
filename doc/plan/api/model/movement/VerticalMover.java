/**
 * Subclass of Movement Interface that moves vertically
 */
public interface HorizontalMover extends Movement{

  /**
   * Move vertically
   */
  public void changeVerticalVelocity(int yVelocityChange);

}