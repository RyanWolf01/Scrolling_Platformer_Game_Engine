package ooga.model.collisions.collision_handling;

import static ooga.Main.COLLISION_CHART_PATH;

import ooga.controller.JSONInformationDecoder;

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
    JSONInformationDecoder decoder = new JSONInformationDecoder();
    return decoder.makeCollisionDataFromJSONObject(COLLISION_CHART_PATH, entityType);
  }
}