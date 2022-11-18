package ooga.model.collisions.collision_handling;

import java.util.Map;
import ooga.model.collisions.data.ActionDataContainer;

//TODO: Allow the user to call multiple actions, and allow actions to take parameters. Add Error
//TODO: checking to this.

/**
 * Represents one row in a CollisionChart. Contains all the different criteria that a collision with
 * collisionData must match in order for some Action to be returned.
 */
public class Criteria {

  private static final String ACTION_PACKAGE_PATH = "ooga.model.actions.";
  private final Map<String, String> myCriteria;
  private final ActionDataContainer myImmutableActionDataContainer;

  /**
   * Initialize a new Criteria class. Takes a hashmap representing the criteria for this collision
   * and a String representing the Action class that will, via reflection, be turned into a class to
   * be called on the Entity that owns this chart containing this Criteria
   *
   * @param criteria          represents each of the attributes that must be satisfied for an Action
   *                          to be applied
   * @param actionDataContainer the data regarding the actions that should be applied if this
   *                                criteria matches
   */
  public Criteria(Map<String, String> criteria, ActionDataContainer actionDataContainer) {
    myCriteria = criteria;
    myImmutableActionDataContainer = actionDataContainer;
  }

  /**
   * Returns true if the CollisionData matches the criteria defined by this object. Else returns
   * false
   *
   * @param collisionData the CollisionData (from perspective of the Entity this Criteria belongs
   *                      to)
   * @return whether CollisionData matches with criteria defined
   */
  public boolean matches(CollisionData collisionData) {
    for (String key : myCriteria.keySet()) {

      // make sure collisionData has all keys in myCriteria
      if (!collisionData.hasKey(key)) {
        return false;
      }

      // make sure that all kv-pairs in myCriteria match those in collisionData
      if (!collisionData.get(key).equals(myCriteria.get(key))) {
        return false;
      }
    }

    return true;
  }

  /**
   * Return the Action associated with these criteria. ONLY works if matches(collisionData) returns
   * true, otherwise throws an error to prevent improper usage
   *
   * @param collisionData the CollisionData of this collision
   * @return Action to be taken
   */
  public ActionDataContainer getActionDatas(CollisionData collisionData) {
    if (matches(collisionData)) {
      return myImmutableActionDataContainer;
    } else {
      throw new RuntimeException("Cannot return action for collisionData that doesn't match"
          + "with the criteria defined by this Criteria object. Improper usage of getAction");
    }
  }

}
