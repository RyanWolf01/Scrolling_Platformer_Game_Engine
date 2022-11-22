package ooga.model.collisions.collision_handling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.entities.data.ImmutableInfo;

/**
 * Encapsulates the data representing a collision between Entity A and Entity B. The data is from
 * the perspective of A, such that A is the target entity upon which Actions will be applied.
 * Calling new CollisionData(A_Info, B_Info, physicsData) is NOT equivalent to calling new
 * CollisionData(B_Info, A_Info, physicsData)!
 */
public class CollisionData implements Iterable<String> {
  public static final String MY_PREFIX = "MY_";
  public static final String OPPONENT_PREFIX = "OPPONENT_";
  public static final String COLLISION_PREFIX = "COLLISION_";
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
   * @param collisionPhysicsInfo data representing collision's physics (e.g. which side was hit,
   *                             from the perspective of Entity A).
   */

  public CollisionData(ImmutableInfo entityAInfo, ImmutableInfo entityBInfo,
      ImmutableInfo collisionPhysicsInfo) {
    data = new HashMap<>();
    addKeys(data, entityAInfo, MY_PREFIX);
    addKeys(data, entityBInfo, OPPONENT_PREFIX);
    addKeys(data, collisionPhysicsInfo, COLLISION_PREFIX);
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

  /**
   * toString method that prints out all the key-value pairs in the CollisionChart
   * @return String kv-pairs
   */
  @Override
  public String toString() {
    return data.toString();
  }

  // Add the keys in ImmutableEntityInfo to data
  private void addKeys(Map<String, String> data, ImmutableInfo entityInfo, String prefix) {
    for (String key : entityInfo) {
      data.put(prefix + key, entityInfo.get(key));
    }
  }

}
