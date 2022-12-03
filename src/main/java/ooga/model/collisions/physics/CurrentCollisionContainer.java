package ooga.model.collisions.physics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;

/**
 * Wrapper class that encapsulates a hashmap of <Entity, CollisionPhysicsInfo>
 */
public class CurrentCollisionContainer implements Iterable<Entity> {
  private final Map<Entity, CollisionPhysicsInfo> myCurrentCollisions;

  public CurrentCollisionContainer() {
    myCurrentCollisions = new HashMap<>();
  }

  public CollisionPhysicsInfo get(ImmutableEntity entity) {
    return myCurrentCollisions.get(entity);
  }

  public CollisionPhysicsInfo set(Entity entity, CollisionPhysicsInfo collisionPhysicsInfo) {
    return myCurrentCollisions.put(entity, collisionPhysicsInfo);
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
  public Iterator<Entity> iterator() {
    return myCurrentCollisions.keySet().iterator();
  }

}
