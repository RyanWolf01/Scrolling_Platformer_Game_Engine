package ooga.model.collisions.collisionhandling;

import java.util.Map;

/**
 * Stores all the CollisionCharts for this program in memory. Access a CollisionChart by passing the
 * CollisionChart's path to getCollisionChart. Accesses CollisionCharts faster than the
 * DefaultCollisionChartReader
 */
public class PreloadedCollisionChartGetter implements CollisionChartGetter {

  private Map<String, CollisionChart> myCollisionCharts;
  private CollisionChartReader myCollisionChartReader;

  /**
   * Return the CollisionChart at the path specified. Path should be relative to the resources
   * folder
   *
   * @param collisionChartPath path in the resources folder to this CollisionChart
   * @return CollisionChart
   */
  @Override
  public CollisionChart getCollisionChart(String collisionChartPath) {
    return null;
//    return myCollisionCharts.get(collisionChartPath);
  }

  /**
   * Return the CollisionChart at the path specified. Path should be relative to the resources
   *
   * @param type the type of this Entity whose collision chart is being found
   * @param collisionChartPath the path of this CollisionChart relative to the resources folder
   * @return CollisionChart
   */
  @Override
  public CollisionChart getCollisionChart(String type, String collisionChartPath) {
    return null;
  }
}