package ooga.model.entities;

public interface ImmutableEntityInfo {
  String TYPE_KEY = "TYPE";

  String get(String key);
  boolean hasKey(String key);
}
