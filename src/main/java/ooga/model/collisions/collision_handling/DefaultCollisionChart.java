package ooga.model.collisions.collision_handling;

import java.util.Collection;
import java.util.Iterator;

public class DefaultCollisionChart implements CollisionChart {
  Collection<Criteria> myCriteriaCollection;

  @Override
  public Iterator<Criteria> iterator() {
    return myCriteriaCollection.iterator();
  }
}
