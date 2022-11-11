package ooga.model.collisions.collision_handling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.entities.ImmutableEntityInfo;

public class CollisionData implements Iterable<String> {
  private final Map<String, String> data;

  public CollisionData(ImmutableEntityInfo entityAInfo, ImmutableEntityInfo entityBInfo, CollisionPhysicsData collisionPhysicsData) {
    data = new HashMap<>();
    addKeys(data, entityAInfo, "MY_");
    addKeys(data, entityAInfo, "OTHER_");
    addKeys(data, collisionPhysicsData);
  }

  public String get(String key) {
    return data.get(key);
  }

  public boolean hasKey(String key) {
    return data.containsKey(key);
  }

  @Override
  public Iterator<String> iterator() {
    return data.keySet().iterator();
  }

  private void addKeys(Map<String, String> data, ImmutableEntityInfo entityInfo, String prefix) {
    for (String key : entityInfo) {
      data.put(prefix + key, entityInfo.get(key));
    }
  }

  private void addKeys(Map<String, String> data, CollisionPhysicsData collisionPhysicsData) {
    data.put("COLLISION_DIRECTION", collisionPhysicsData.collisionDirection().toString());
  }
}
