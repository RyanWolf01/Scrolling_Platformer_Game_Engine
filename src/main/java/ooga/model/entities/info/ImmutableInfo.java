package ooga.model.entities.info;

import java.util.ResourceBundle;

public interface ImmutableInfo extends Iterable<String>{
  ResourceBundle entityInfoResources = ResourceBundle.getBundle("properties/entityInfo");
  String COLLIDABLE_TYPE_KEY = entityInfoResources.getString("type");

  String get(String key);
  boolean hasKey(String key);

}
