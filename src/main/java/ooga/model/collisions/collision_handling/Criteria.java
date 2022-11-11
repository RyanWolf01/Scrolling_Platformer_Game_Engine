package ooga.model.collisions.collision_handling;

import java.util.Collection;
import java.util.Map;
import ooga.model.actions.Action;
import ooga.model.collisions.CollisionData;
import ooga.model.entities.ImmutableEntityInfo;

public class Criteria {
  private Map<String, String> myCriteria;
  private String myActionClassString;
  public final String COLLISION_DIRECTION_KEY = "COLLISION_DIRECTION";

  public Criteria(Map<String, String> criteria, String actionClassString) {
    myCriteria = criteria;
    myActionClassString = actionClassString;
  }

  public boolean matches(ImmutableEntityInfo sourceEntityInfo, CollisionData collisionData) {
    int count = 0;
    for (String key : myCriteria.keySet()) {

      if (sourceEntityInfo.hasKey(key)) {
        if (sourceEntityInfo.get(key).equals(myCriteria.get(key))) {
          count += 1;
        }
        else {
          return false;
        }
      }

    }

    String collisionDirection = collisionData.collisionDirection().toString();
    return count == myCriteria.size() && collisionDirection.equals(
        myCriteria.get(COLLISION_DIRECTION_KEY));
  }

  // implement with reflection
  public Action getAction() {
    return null;
  }
}
