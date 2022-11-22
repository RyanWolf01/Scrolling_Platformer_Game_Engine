package ooga.model.collisions.collisionhandling;

/**
 * Return the CollisionChart that exists at the path specified
 */
public interface CollisionChartGetter {

  /**
   * Return the CollisionChart that exists at the path specified by collisionChartPath
   *
   * @param type the type of this Entity whose collision chart is being found
   * @return
   */
  CollisionChart getCollisionChart(String type);

  /**
   * Return the CollisionChart that exists at the path specified by collisionChartPath
   *
   * @param type the type of this Entity whose collision chart is being found
   * @param collisionChartPath the path of this CollisionChart relative to the resources folder
   * @return
   */
  CollisionChart getCollisionChart(String type, String collisionChartPath);

}
