package ooga.model.collisions.collisionhandling;

import ooga.controller.JSONInformationDecoder;

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
  CollisionChart getCollisionChart(JSONInformationDecoder decoder, String type);

}
