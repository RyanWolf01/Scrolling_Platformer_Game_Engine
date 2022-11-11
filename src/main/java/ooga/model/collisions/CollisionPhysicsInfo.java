package ooga.model.collisions;

import ooga.model.Info;
import ooga.model.collisions.data.CollisionDirection;

public class CollisionPhysicsInfo extends Info {

  public static final String COLLISION_DIRECTION_KEY = "DIRECTION";
  private final boolean collisionOccurred;

  public CollisionPhysicsInfo(boolean collisionOccurred, CollisionDirection collisionDirection) {
    this.collisionOccurred = collisionOccurred;
    super.set(COLLISION_DIRECTION_KEY, collisionDirection.toString());
  }

  public boolean collisionOccurred() {
    return collisionOccurred;
  }
}
