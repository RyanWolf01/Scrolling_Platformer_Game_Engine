package ooga.model.entities.enemies;

import ooga.model.Info;
import ooga.model.entities.CollidableEntity;

public abstract class Enemy extends CollidableEntity {

  /**
   * abstract Enemy class that can neither move nor have lives
   * @param initialXCoordinate
   * @param initialYCoordinate
   * @param height
   * @param width
   * @param entityInfo
   */
  public Enemy(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

}
