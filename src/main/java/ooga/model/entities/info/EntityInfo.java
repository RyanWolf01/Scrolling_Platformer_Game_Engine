package ooga.model.entities.info;

public class EntityInfo extends Info {

  public EntityInfo(String type) {
    super.set(COLLIDABLE_TYPE_KEY, type);
  }
}
