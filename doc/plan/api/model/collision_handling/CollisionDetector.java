/**
 * Determines if two entities have collided. This returns a CollisionInformation object. If
 * a collision has not occurred, a property in CollisionInformation (called collisionOccured) is
 * false. Otherwise this property is true, and the CollisionInformation object contains additional
 * information about the collision (whether it was a side collision/top/bottom collision, could also
 * contain info about the medium in which the collision occured (e.g. water), etc. )
 */
public interface CollisionDetector {
  CollisionInformation detectCollision(Entity entityA, Entity entityB);
}