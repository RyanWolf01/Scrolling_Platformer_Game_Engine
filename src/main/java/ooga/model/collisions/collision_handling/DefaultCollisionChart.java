package ooga.model.collisions.collision_handling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ooga.model.actions.Action;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.collisions.collision_handling.exceptions.NoCollisionCriteriaMatchException;
import ooga.model.entities.ImmutableEntityInfo;

public class DefaultCollisionChart implements CollisionChart {
  private final Collection<Criteria> myCriteria;

  public DefaultCollisionChart() {
    myCriteria = new ArrayList<>();

    // example of adding a row in the collision chart (called a Criteria object)
    Map<String, String> criteriaMap = new HashMap<>();
    criteriaMap.put(ImmutableEntityInfo.TYPE_KEY, "Platform");
    myCriteria.add(new Criteria(criteriaMap, "Jump"));

  }

  @Override
  public Action getAction(CollisionData collisionData) {
    for (Criteria criteria : myCriteria) {
      if (criteria.matches(collisionData)) {
        return criteria.getAction();
      }
    }

    throw new NoCollisionCriteriaMatchException("This collision chart doesn't define how to handle"
        + " this type of collision between these two Entities");
  }
}
