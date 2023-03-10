package ooga.model.entities.entitymodels;

import ooga.model.entities.info.ImmutableInfo;

public interface ImmutableEntity {

  /**
   *
   * @return x coordinate
   */
  double getXCoordinate();

  /**
   *
   * @return y coordinate
   */
  double getYCoordinate();

  /**
   * Need this getter in the PhysicsCalculator class
   * @return height of entity
   */
  double getHeight();

  /**
   * Need this getter in the PhysicsCalculator class
   * @return width of entity
   */
  double getWidth();

  /**
   *
   * @return immutable version of entity info
   */
  ImmutableInfo getImmutableEntityInfo();
}
