package ooga.model.entities;

import ooga.model.entities.data.Info;

public class StaticEntity extends Entity{

  public StaticEntity(int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }
}
