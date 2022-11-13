package ooga.model.entities;

import ooga.model.Info;

public class EntityInfo extends Info {

  public EntityInfo(String type) {
    super.set(TYPE_KEY, type);
  }
}
