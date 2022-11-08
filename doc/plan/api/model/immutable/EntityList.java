/**
 * EntityList (will be abstract class) that holds all the entities in a given games. By using the
 * ImmutableEntity interface on returning a given Entity, it is possible to control the data access
 * of different users, such as the view or maybe the Controller, preventing unwanted users from altering
 * the state of the data.
 */
public interface EntityList {

  /**
   *
   * @param entity Entity to be added
   */
  public void addEntity(Entity entity);

  /**
   *
   * @param index Index in list
   * @return ImmutableEntity version of current Entity in list
   */
  public ImmutableEntity getEntity(int index);


}