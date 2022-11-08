/**
 * This is the class that handles the collision of two entities. The CollisionHandler takes in
 * the two entities that collided, along with information about the collision. Then it gets the
 * state information of entityA and provides it, along with collisionInformation, to entityB. The
 * same is done for entityB. Then, entityA and entityB determine how to react to the collisions
 * based on this information.
 */
public interface CollisionHandler {
  void handleCollision(CollisionInformation collisionInformation, Entity entityA, Entity entityB);
}