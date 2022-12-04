package ooga.model.collisions.physics;

import ooga.model.entities.info.Info;

public class CollisionPhysicsInfo extends Info {

  public static final String COLLISION_DIRECTION_KEY = "DIRECTION";
  public static final String NUM_CONSECUTIVE_COLLISIONS_KEY = "NUM_CONSECUTIVE_COLLISIONS";
  private boolean collisionIsFresh;
  private CollisionDirection collisionDirection;

  public CollisionPhysicsInfo(boolean collisionIsFresh, int numConsecutiveCollisions, CollisionDirection collisionDirection) {
    this.collisionIsFresh = collisionIsFresh;
    super.set(COLLISION_DIRECTION_KEY, collisionDirection.toString());
    super.set(NUM_CONSECUTIVE_COLLISIONS_KEY, Integer.toString(numConsecutiveCollisions));
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
    return Integer.parseInt(super.get(NUM_CONSECUTIVE_COLLISIONS_KEY));
  }

  public void incrementNumConsecutiveCollisions() {
    super.set(NUM_CONSECUTIVE_COLLISIONS_KEY, Integer.toString(getNumConsecutiveCollisions() + 1));
  }


}
