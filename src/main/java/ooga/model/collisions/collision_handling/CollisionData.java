package ooga.model.collisions.collision_handling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.entities.ImmutableEntityInfo;

/**
 * Encapsulates the data representing a collision between Entity A and Entity B. The data is from
 * the perspective of A, such that A is the target entity upon which Actions will be applied.
 * Calling new CollisionData(A_Info, B_Info, physicsData) is NOT equivalent to calling new
 * CollisionData(B_Info, A_Info, physicsData)!
 */
public class CollisionData implements Iterable<String> {

  private final Map<String, String> data;

  /**
   * Instantiates a new CollisionData object representing a collision between Entity A and Entity B
   * with collision physics attributes collisionPhysicsData. The data is from the perspective of A,
   * such that A is the target entity upon which Actions will be applied. Calling new
   * CollisionData(A_Info, B_Info, physicsData) is NOT equivalent to calling new
   * CollisionData(B_Info, A_Info, physicsData)!
   *
   * @param entityAInfo          entityA's info
   * @param entityBInfo          entityB's info
   * @param collisionPhysicsData data representing collision's physics (e.g. which side was hit,
   *                             from the perspective of Entity A).
   */

  public CollisionData(ImmutableEntityInfo entityAInfo, ImmutableEntityInfo entityBInfo,
      CollisionPhysicsData collisionPhysicsData) {
    data = new HashMap<>();
    addKeys(data, entityAInfo, "MY_");
    addKeys(data, entityBInfo, "OTHER_");
    addKeys(data, collisionPhysicsData);
  }

  /**
   * Get the String info associated with the key specified.
   *
   * @param key the key
   * @return String value
   */
  public String get(String key) {
    return data.get(key);
  }

  /**
   * Returns whether this CollisionData has the key specified
   *
   * @param key the key
   * @return if the CollisionData contains this String
   */
  public boolean hasKey(String key) {
    return data.containsKey(key);
  }

  /**
   * Get the Iterator for this CollisionData object
   *
   * @return Iterator<String>
   */
  @Override
  public Iterator<String> iterator() {
    return data.keySet().iterator();
  }

  // Add the keys in ImmutableEntityInfo to data
  private void addKeys(Map<String, String> data, ImmutableEntityInfo entityInfo, String prefix) {
    for (String key : entityInfo) {
      data.put(prefix + key, entityInfo.get(key));
    }
  }

  // Add keys corresponding with CollisionPhysicsData
  // TODO: Make this more extendable so that there can be all sorts of collisionPhysicsData that
  // TODO: automatically be added. Should transition away from using a record for CollisionPhysicsData
  // TODO: to do this
  private void addKeys(Map<String, String> data, CollisionPhysicsData collisionPhysicsData) {
    data.put("COLLISION_DIRECTION", collisionPhysicsData.collisionDirection().toString());
  }
}
