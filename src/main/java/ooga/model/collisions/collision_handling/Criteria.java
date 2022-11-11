package ooga.model.collisions.collision_handling;

import java.util.Map;
import ooga.model.actions.Action;
import ooga.model.collisions.CollisionPhysicsData;
import ooga.model.entities.ImmutableEntityInfo;

public class Criteria {
  private Map<String, String> myCriteria;
  private String myActionClassString;

  public Criteria(Map<String, String> criteria, String actionClassString) {
    myCriteria = criteria;
    myActionClassString = actionClassString;
  }

  public boolean matches(CollisionData collisionData) {
    for (String key : myCriteria.keySet()) {

      // make sure collisionData has all keys in myCriteria
      if (! collisionData.hasKey(key)) {
        return false;
      }

      // make sure that all kv-pairs in myCriteria match those in collisionData
      if (! collisionData.get(key).equals(myCriteria.get(key))) {
        return false;
      }
    }

    return true;
  }

  // implement with reflection
  public Action getAction() {
    return null;
  }
}
