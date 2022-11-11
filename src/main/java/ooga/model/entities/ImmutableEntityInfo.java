package ooga.model.entities;

public interface ImmutableEntityInfo {
  String TYPE_KEY = "TYPE";
  String COLLISION_CHART_KEY = "COLLISION_CHART";

  String get(String key);
  boolean hasKey(String key);
}
