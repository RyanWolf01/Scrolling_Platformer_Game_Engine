package ooga.model.collisions.physics;

import ooga.model.entities.info.Info;

public class CollisionPhysicsInfo extends Info {

  public static final String COLLISION_DIRECTION_KEY = "DIRECTION";
  private boolean collisionIsFresh;
  private int numConsecutiveCollisions;

  public CollisionPhysicsInfo(boolean collisionIsFresh, int numConsecutiveCollisions, CollisionDirection collisionDirection) {
    this.collisionIsFresh = collisionIsFresh;
    super.set(COLLISION_DIRECTION_KEY, collisionDirection.toString());
  }

  public boolean collisionIsFresh() {
    return collisionIsFresh;
  }

  public void setCollisionIsFresh(boolean collisionIsFresh) {
    this.collisionIsFresh = collisionIsFresh;
  }

  public int getNumConsecutiveCollisions() {
    return numConsecutiveCollisions;
  }

  public void incrementNumConsecutiveCollisions() {
    numConsecutiveCollisions += 1;
  }


}
