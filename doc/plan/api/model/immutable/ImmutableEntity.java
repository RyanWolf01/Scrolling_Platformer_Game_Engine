/**
 * Basic version of Entity that ensures limited functionality of Entity class. This is an example of how
 * interfaces will be used to control data transfers. This will likely be changed later in the project, though,
 * to allow for more functionality.
 */
public interface ImmutableEntity {

  /**
   *
   * @return x coordinate
   */
  public int getXCoordinate();

  /**
   *
   * @return y coordinate
   */
  public int getYCoordinate();


}