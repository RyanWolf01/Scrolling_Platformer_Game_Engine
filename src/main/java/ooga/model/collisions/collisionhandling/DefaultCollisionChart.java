package ooga.model.collisions.collisionhandling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ooga.model.collisions.collisionhandling.exceptions.NoCollisionCriteriaMatchException;
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
  }

  public DefaultCollisionChart() {
    myCriteria = new ArrayList<>();
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
        + " this type of collision between these two Entities. Here's the CollisionData: " +
        collisionData.toString());
  }

  @Override
  public void addCriteria(Criteria criteria) {
    myCriteria.add(criteria);
  }
}
