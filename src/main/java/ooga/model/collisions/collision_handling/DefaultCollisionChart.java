package ooga.model.collisions.collision_handling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ooga.model.collisions.collision_handling.exceptions.NoCollisionCriteriaMatchException;
import ooga.model.collisions.actiondata.ActionDataContainer;

/**
 * A DefaultCollisionChart that implements CollisionChart and contains returns an Action based on
 * the CollisionData given
 */
public class DefaultCollisionChart implements CollisionChart {

  private final Collection<Criteria> myCriteria;

  /**
   * Instantiates a new DefaultCollisionChart
   */

  public DefaultCollisionChart(List<Criteria> criteriaList) {
    myCriteria = criteriaList;

    // example of adding a row in the collision chart (called a Criteria object)
//    Map<String, String> criteriaMap = new HashMap<>();
//    criteriaMap.put(ImmutableInfo.TYPE_KEY, "Platform");
//    myCriteria.add(new Criteria(criteriaMap, "Jump"));

  }

  /**
   * Takes the CollisionData for a collision and outputs what action should be applied
   *
   * @param collisionData the data representing the entities involved with the collision and the
   *                      collision physics
   * @return Action
   */
  @Override
  public ActionDataContainer getActionDatas(CollisionData collisionData) {
    for (Criteria criteria : myCriteria) {
      if (criteria.matches(collisionData)) {
        return criteria.getActionDatas(collisionData);
      }
    }

    throw new NoCollisionCriteriaMatchException("This collision chart doesn't define how to handle"
        + " this type of collision between these two Entities");
  }
}
