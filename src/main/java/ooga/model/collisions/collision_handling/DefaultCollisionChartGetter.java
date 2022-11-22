package ooga.model.collisions.collision_handling;

import static ooga.Main.COLLISION_CHART_PATH;

import ooga.controller.JSONInformationDecoder;
import ooga.model.collisions.collision_handling.exceptions.CollisionChartParsingException;

/**
 * Accesses CollisionCharts by reading them in from a file at the specified path for every access.
 */
public class DefaultCollisionChartGetter implements CollisionChartGetter {

  /**
   * Return the CollisionChart at the path specified. Path should be relative to the resources
   * folder
   *
   * @param entityType the type of this entity (e.g. "GOOMBA")
   * @return CollisionChart
   */
  @Override
  public CollisionChart getCollisionChart(String entityType) {
    return getCollisionChart(entityType, COLLISION_CHART_PATH);
  }

  @Override
  public CollisionChart getCollisionChart(String entityType, String collisionChartPath) {
    try {
      JSONInformationDecoder decoder = new JSONInformationDecoder();
      return decoder.makeCollisionDataFromJSONObject(collisionChartPath, entityType);
    } catch (RuntimeException e) {
      throw new CollisionChartParsingException("An exception occurred while attempting to parse the"
          + " collision chart for type " + entityType + ". The specified path for the "
          + "CollisionChart is: " + collisionChartPath, e);
    }

  }
}