package ooga.model.entities.info;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Info implements ImmutableInfo {
  private Map<String, String> map = new HashMap<>();

  @Override
  public String get(String key) {
    return map.get(key);
  }

  @Override
  public boolean hasKey(String key) {
    return map.containsKey(key);
  }

  @Override
  public Iterator<String> iterator() {
    return map.keySet().iterator();
  }

  public void set(String key, String val) {
    map.put(key, val);
  }
}
