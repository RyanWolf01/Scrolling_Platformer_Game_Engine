package ooga.model.entities.info;

public interface ImmutableInfo extends Iterable<String>{
  String TYPE_KEY = "TYPE";
  String COLLISION_CHART_KEY = "COLLISION_CHART";

  String get(String key);
  boolean hasKey(String key);

}
