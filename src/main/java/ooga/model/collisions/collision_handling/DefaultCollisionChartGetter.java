package ooga.model.collisions.collision_handling;

/**
 * Accesses CollisionCharts by reading them in from a file at the specified path for every access.
 */
public class DefaultCollisionChartGetter implements CollisionChartGetter {

  private CollisionChartReader myCollisionChartReader;

  /**
   * Instantiates a new DefaultCollisionChartReader
   */
  public DefaultCollisionChartGetter() {
    myCollisionChartReader = new CollisionChartReader();
  }

  /**
   * Return the CollisionChart at the path specified. Path should be relative to the resources
   * folder
   *
   * @param collisionChartPath path in the resources folder to this CollisionChart
   * @return CollisionChart
   */
  @Override
  public CollisionChart getCollisionChart(String collisionChartPath) {
    return myCollisionChartReader.readCollisionChart(collisionChartPath);
  }
}