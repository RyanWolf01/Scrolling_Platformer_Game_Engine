package ooga.model.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityInfo implements ImmutableEntityInfo {
  private Map<String, String> map = new HashMap<>();

  public EntityInfo(String type) {
    map.put(TYPE_KEY, type);
  }

  @Override
  public String get(String key) {
    return map.get(key);
  }

  @Override
  public boolean hasKey(String key) {
    return map.containsKey(key);
  }

  public void set(String key, String val) {
    map.put(key, val);
  }
}
