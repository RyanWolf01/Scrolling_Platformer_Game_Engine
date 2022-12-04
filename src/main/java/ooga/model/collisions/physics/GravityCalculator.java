package ooga.model.collisions.physics;

import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;

public class GravityCalculator {

  /**
   * calculates if an Entity is in the Air
   * @return boolean
   */
  public boolean checkInAir(Entity entity){

    if(!entity.getMyCurrentCollisions().hasCollisions())
      return true;

    ImmutableEntity collided = entity.getMyCurrentCollisions().iterator().next();
    CollisionPhysicsInfo collisionPhysicsInfo = entity.getMyCurrentCollisions().get(collided);
    if(collided.getImmutableEntityInfo().get("TYPE").equals("static_platform") &&
        collisionPhysicsInfo.getCollisionDirection() == CollisionDirection.BOTTOM)
      return false;

    return true;
  }

}
