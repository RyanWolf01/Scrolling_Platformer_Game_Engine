package ooga.model.collisions.collision_handling;

/**
 * Return the CollisionChart that exists at the path specified
 */
public interface CollisionChartReader {

  /**
   * Return the CollisionChart that exists at the path specified by collisionChartPath
   *
   * @param collisionChartPath the path of this CollisionChart relative to the resources folder
   * @return
   */
  CollisionChart getCollisionChart(String collisionChartPath);

}
