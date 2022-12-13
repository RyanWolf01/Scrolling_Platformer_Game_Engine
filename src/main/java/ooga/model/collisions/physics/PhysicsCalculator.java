package ooga.model.collisions.physics;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MainCharacter;
import ooga.view.nodes.ScrollingNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhysicsCalculator {

  private static final Logger LOG = LogManager.getLogger(MainCharacter.class);

  /**
   * This will enact the rules on the first entity, Entity a
   *
   * @param collider the first entity, to be acted on
   * @param collided the second entity, that is collided with
   */
  public CollisionPhysicsData calculatePhysicsData(CollidableEntity collider, Entity collided) {
    throwExceptionIfNotColliding(collider, collided);
    DirectionDistancePair ddp = checkDirection(collider, collided);
    CollisionPhysicsData info = new CollisionPhysicsData(true, 1, ddp.direction);
    collider.getMyCurrentCollisions().set(collided, info);
    return info;
  }

  /**
   * Updates the CollisionPhysicsInfo parameter by incrementing the number of consecutive collisions
   * that have occurred in it.
   * @param currCollisionPhysicsData the PhysicsInfo object to be updated
   * @return currCollisionPhysicsInfo the updated PhysicsInfoObject
   */
  public CollisionPhysicsData updatePhysicsDataOfCurrentCollision(
      CollisionPhysicsData currCollisionPhysicsData) {
    currCollisionPhysicsData.incrementNumConsecutiveCollisions();
    return currCollisionPhysicsData;
  }

  /**
   * Check if two entities are colliding with each other
   * @param collider Entity that's initiating the collision
   * @param collided Entity that's being collided with
   * @return entitiesAreColliding
   */
  public boolean areColliding(CollidableEntity collider, Entity collided) {
    if (collider.getXCoordinate() + collider.getWidth() < collided.getXCoordinate()) return false;
    if (collider.getXCoordinate() > collided.getXCoordinate() + collided.getWidth()) return false;
    if (collider.getYCoordinate() + collider.getHeight() < collided.getYCoordinate()) return false;
    if (collider.getYCoordinate() > collided.getYCoordinate() + collided.getHeight()) return false;

    return true;
  }

// collider.getImmutableEntityInfo().get(ImmutableInfo.COLLIDABLE_TYPE_KEY).equalsIgnoreCase("MARIO") && collider.getYCoordinate() < 50
  private void throwExceptionIfNotColliding(CollidableEntity collider, Entity collided) {
    if (!areColliding(collider, collided)) {
      throw new EntitiesNotCollidingException();
    }
  }

  private DirectionDistancePair checkDirection(Entity collider, Entity collided) {
    return positionApproach(collider, collided);
  }

  // Return a DistanceDirectionPair that contains the direction (from the perspective of collider)
  // in which this collision is occurring, along with the distance that collider has moved inside
  // of collided.
  private DirectionDistancePair positionApproach(Entity collider, Entity collided) {
    List<DirectionDistancePair> directionDistancePairs = new ArrayList<>();
    directionDistancePairs.add(new DirectionDistancePair(CollisionDirection.TOP, collider.getYCoordinate() - (collided.getYCoordinate() + collided.getHeight())));
    directionDistancePairs.add(new DirectionDistancePair(CollisionDirection.BOTTOM, (collider.getYCoordinate() + collider.getHeight()) - collided.getYCoordinate()));
    directionDistancePairs.add(new DirectionDistancePair(CollisionDirection.LEFT, collider.getXCoordinate() - (collided.getXCoordinate() + collided.getWidth())));
    directionDistancePairs.add(new DirectionDistancePair(CollisionDirection.RIGHT, (collider.getXCoordinate() + collider.getWidth()) - collided.getXCoordinate()));

    DirectionDistancePair minddp = directionDistancePairs.remove(0);
    for (DirectionDistancePair ddp : directionDistancePairs) {
      if (Math.abs(ddp.distance) < Math.abs(minddp.distance)) {
        minddp = ddp;
      }
    }
    return minddp;

  }

  private class DirectionDistancePair {
    private CollisionDirection direction;
    private Double distance;
    private DirectionDistancePair(CollisionDirection direction, Double distance) {
      this.direction = direction;
      this.distance = distance;
    }
  }

}
