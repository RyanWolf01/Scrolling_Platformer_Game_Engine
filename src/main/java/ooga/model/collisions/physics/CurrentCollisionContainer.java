package ooga.model.collisions.physics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;

/**
 * Wrapper class that encapsulates a hashmap of <Entity, CollisionPhysicsInfo>
 */
public class CurrentCollisionContainer implements Iterable<ImmutableEntity> {
  private final Map<ImmutableEntity, CollisionPhysicsData> myCurrentCollisions;

  public CurrentCollisionContainer() {
    myCurrentCollisions = new HashMap<>();
  }

  public CollisionPhysicsData get(ImmutableEntity entity) {
    return myCurrentCollisions.get(entity);
  }

  public CollisionPhysicsData set(Entity entity, CollisionPhysicsData collisionPhysicsData) {
    return myCurrentCollisions.put(entity, collisionPhysicsData);
  }

  public boolean containsKey(ImmutableEntity entity) {
    return myCurrentCollisions.containsKey(entity);
  }

  public void remove(ImmutableEntity entity) {
    myCurrentCollisions.remove(entity);
  }

  /**
   * check if current collision container contains anything
   * @return boolean
   */
  public boolean hasCollisions(){
    if(myCurrentCollisions.keySet().size() != 0)
      return true;
    return false;
  }
  @Override
  public Iterator<ImmutableEntity> iterator() {
    return myCurrentCollisions.keySet().iterator();
  }

}
