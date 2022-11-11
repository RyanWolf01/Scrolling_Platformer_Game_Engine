package ooga.model.collisions.collision_handling;

/**
 * Stores all the CollisionCharts for this program in memory. Access a CollisionChart by
 * passing the CollisionChart's path to getCollisionChart. Accesses CollisionCharts faster than
 * the DefaultCollisionChartReader
 */
public class PreloadedCollisionChartReader implements CollisionChartReader {

  /**
   * Return the CollisionChart at the path specified. Path should be relative to the
   * resources folder
   * @param collisionChartPath path in the resources folder to this CollisionChart
   * @return CollisionChart
   */
  @Override
  public CollisionChart getCollisionChart(String collisionChartPath) {
    return null;
  }
}
