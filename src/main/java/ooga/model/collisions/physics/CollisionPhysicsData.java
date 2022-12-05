package ooga.model.collisions.physics;

import ooga.model.entities.info.ImmutableInfo;
import ooga.model.entities.info.Info;

public class CollisionPhysicsData {

  public static final String COLLISION_DIRECTION_KEY = "DIRECTION";
  public static final String NUM_CONSECUTIVE_COLLISIONS_KEY = "NUM_CONSECUTIVE_COLLISIONS";
  private boolean collisionIsFresh;
  private int numConsecutiveCollisions;
  private final CollisionDirection collisionDirection;

  public CollisionPhysicsData(boolean collisionIsFresh, int numConsecutiveCollisions, CollisionDirection collisionDirection) {
    this.collisionIsFresh = collisionIsFresh;
    this.numConsecutiveCollisions = numConsecutiveCollisions;
    this.collisionDirection = collisionDirection;
  }

  public CollisionDirection getCollisionDirection(){
    return collisionDirection;
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

  public ImmutableInfo toImmutableInfoObject() {
    Info info = new Info();
    info.set(NUM_CONSECUTIVE_COLLISIONS_KEY, Integer.toString(numConsecutiveCollisions));
    info.set(COLLISION_DIRECTION_KEY, collisionDirection.toString());
    return info;
  }

}
