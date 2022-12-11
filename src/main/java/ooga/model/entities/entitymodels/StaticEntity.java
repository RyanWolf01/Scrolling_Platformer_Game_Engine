package ooga.model.entities.entitymodels;

import ooga.model.entities.info.Info;

public class StaticEntity extends Entity{

  /**
   * Static Entity has no lives and cannot move. This does not change state when another entity collides with it.
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public StaticEntity(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }
}
