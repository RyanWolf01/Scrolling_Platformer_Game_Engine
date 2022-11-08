/**
 * Takes the collisionInformation containing information about the characteristics of the collision
 * and other entity involved with the collision. It compares that to the collision rules charted out
 * for the Entity to which this CollisionChart belongs. This returns an EntityCommand that makes
 * the entity perform some action.
 */
public interface CollisionChart {
  EntityCommand getReactionToCollision(CollisionInformation collisionInformation);
}