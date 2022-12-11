package ooga.model.collisions.physics;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MainCharacter;
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
  public CollisionPhysicsData calculatePhysicsInfoAndMoveColliderOutsideOfCollided(Entity collider, Entity collided) {
    DirectionDistancePair ddp = checkDirectionAndMoveColliderOutsideOfCollided(collider, collided);
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

  private DirectionDistancePair checkDirectionAndMoveColliderOutsideOfCollided(Entity collider, Entity collided) {
//    CollisionDirection collisionDirection = velocityApproach(collider, collided);
//    if (collisionDirection.equals(CollisionDirection.NONE)) {
//      collisionDirection = positionApproach(collider, collided);
//    }
//    return collisionDirection;
    return positionApproach(collider, collided);
  }

  // TODO: Check to make sure these things are actually colliding
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

  private CollisionDirection velocityApproach(Entity collider, Entity collided) {
    List<Edge> edgesA = new ArrayList<>();
    edgesA.add(getTopEdge(collider));
    edgesA.add(getBottomEdge(collider));
    edgesA.add(getLeftEdge(collider));
    edgesA.add(getRightEdge(collider));

    List<Edge> edgesB = new ArrayList<>();
    edgesB.add(getTopEdge(collided));
    edgesB.add(getBottomEdge(collided));
    edgesB.add(getLeftEdge(collided));
    edgesB.add(getRightEdge(collided));

    double minTime = Double.MAX_VALUE;
    CollisionDirection minTimeDirection = CollisionDirection.NONE;

    for (Edge edgeA : edgesA) {
      for (Edge edgeB : edgesB) {
        if (edgeA.willIntersect(edgeB)) {

          double timeToIntersect = edgeA.timeToIntersect(edgeB);
          minTime = Math.min(minTime, timeToIntersect);
          if (minTime == timeToIntersect) {
            minTimeDirection = edgeA.direction;
          }
        }
      }
    }

//    if (minTimeDirection == CollisionDirection.NONE) {
//      throw new RuntimeException("These objects did not collide");
//    }
//    moveColliderOutsideOfCollided(collider, minTime, minTimeDirection);
    return minTimeDirection;
  }

  private void moveColliderOutsideOfCollided(Entity collider, double minTime,
      CollisionDirection minTimeDirection) {
    if (minTimeDirection != CollisionDirection.NONE) {
      double moveBackX = collider.getXVelocity() - (collider.getXVelocity() * minTime);
      double moveBackY = collider.getYVelocity() - (collider.getYVelocity() * minTime);
      if (minTimeDirection == CollisionDirection.BOTTOM) {
//        collider.setYCoordinate(collider.getYCoordinate() - moveBackY - 0.25);
        collider.setYCoordinate(collider.getYCoordinate() - moveBackY);

      }
      else if (minTimeDirection == CollisionDirection.TOP) {
//        collider.setYCoordinate(collider.getYCoordinate() - moveBackY + 0.25);
        collider.setYCoordinate(collider.getYCoordinate() - moveBackY);

      }
      else if (minTimeDirection == CollisionDirection.LEFT) {
//        collider.setXCoordinate(collider.getXCoordinate() - moveBackX + 0.25);
        collider.setXCoordinate(collider.getXCoordinate() - moveBackX);

      }
      else if (minTimeDirection == CollisionDirection.RIGHT) {
//        collider.setXCoordinate(collider.getXCoordinate() - moveBackX - 0.25);
        collider.setXCoordinate(collider.getXCoordinate() - moveBackX);
      }
    }
  }

  private Edge getTopEdge(Entity entity) {
    double xvel = entity.getXVelocity();
    double yvel = entity.getYVelocity();

    return new Edge(CollisionDirection.TOP,
        new Coordinate(entity.getXCoordinate() - xvel, entity.getYCoordinate() - yvel),
        new Coordinate(entity.getXCoordinate() + entity.getWidth() - xvel, entity.getYCoordinate() - yvel),
        xvel, yvel);
  }

  private Edge getBottomEdge(Entity entity) {
    double xvel = entity.getXVelocity();
    double yvel = entity.getYVelocity();

    return new Edge(CollisionDirection.BOTTOM,
        new Coordinate(entity.getXCoordinate() - xvel, entity.getYCoordinate() + entity.getHeight() - yvel),
        new Coordinate(entity.getXCoordinate() + entity.getWidth() - xvel, entity.getYCoordinate() + entity.getHeight() - yvel),
        xvel, yvel);
  }

  private Edge getLeftEdge(Entity entity) {
    double xvel = entity.getXVelocity();
    double yvel = entity.getYVelocity();

    return new Edge(CollisionDirection.LEFT,
        new Coordinate(entity.getXCoordinate() - xvel, entity.getYCoordinate() - yvel),
        new Coordinate(entity.getXCoordinate() - xvel, entity.getYCoordinate() + entity.getHeight() - yvel),
        xvel, yvel);
  }

  private Edge getRightEdge(Entity entity) {
    double xvel = entity.getXVelocity();
    double yvel = entity.getYVelocity();

    return new Edge(CollisionDirection.RIGHT,
        new Coordinate(entity.getXCoordinate() + entity.getWidth() - xvel, entity.getYCoordinate() - yvel),
        new Coordinate(entity.getXCoordinate() + entity.getWidth() - xvel, entity.getYCoordinate() + entity.getHeight() - yvel),
        xvel, yvel);
  }

  private class Coordinate {
    double x, y;
    public Coordinate(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }

  private class Edge {
    CollisionDirection direction;
    Coordinate vertex1;
    Coordinate vertex2;
    double yVelocity;
    double xVelocity;

    public Edge(CollisionDirection side, Coordinate vertex1, Coordinate vertex2, double xVelocity, double yVelocity) {
      if ((vertex1.y == vertex2.y) && (side == CollisionDirection.TOP || side == CollisionDirection.BOTTOM)) {
        direction = side;
      }
      else if ((vertex1.x == vertex2.x) && (side == CollisionDirection.RIGHT || side == CollisionDirection.LEFT)) {
        direction = side;
      }
      else {
        throw new RuntimeException("Improper usage of Edge");
      }

      this.vertex1 = vertex1;
      this.vertex2 = vertex2;
      this.xVelocity = xVelocity;
      this.yVelocity = yVelocity;

    }

    public boolean willIntersect(Edge otherEdge) {
      return timeToIntersect(otherEdge) != Double.MAX_VALUE;
    }

    public double timeToIntersect(Edge otherEdge) {
      if (!bothHorizontalOrVertical(this, otherEdge)) {
        return Double.MAX_VALUE;
      }

      if (this.direction == CollisionDirection.TOP || this.direction == CollisionDirection.BOTTOM) {
        return timeToIntersectTopOrBottom(otherEdge);
      }
      else {
        return timeToIntersectLeftOrRight(otherEdge);
      }
    }

    // TODO: consolidate willIntersectTopOrBottom and willIntersectLeftOrRight into one method
    // IF YOU'RE NOT CHANGING VELOCITIES WITH KEY PRESSES THEN THIS WON'T WORK!!!
    private double timeToIntersectTopOrBottom(Edge otherEdge) {
      // Calculate time for x values to intersect
      double denominator = (otherEdge.yVelocity - this.yVelocity);
      if (denominator == 0) {
        return Double.MAX_VALUE;
      }
      double time = (this.vertex1.y - otherEdge.vertex1.y) / denominator;

      // get the x values at this time
      double thisX1 = this.vertex1.x + this.xVelocity * time;
      double thisX2 = this.vertex2.x + this.xVelocity * time;
      double otherX1 = otherEdge.vertex1.x + otherEdge.xVelocity * time;
      double otherX2 = otherEdge.vertex2.x + otherEdge.xVelocity * time;

      // make sure the x values are in range of each other
      if (thisX2 >= otherX1 && thisX1 <= otherX2) {
        return time;
      }
      else {
        return Double.MAX_VALUE;
      }
    }

    private double timeToIntersectLeftOrRight(Edge otherEdge) {
      // Calculate time for x values to intersect
      double denominator = (otherEdge.xVelocity - this.xVelocity);
      if (denominator == 0) {
        return Double.MAX_VALUE;
      }
      double time = (this.vertex1.x - otherEdge.vertex1.x) / denominator;

      // get the x values at this time
      double thisY1 = this.vertex1.y + this.yVelocity * time;
      double thisY2 = this.vertex2.y + this.yVelocity * time;
      double otherY1 = otherEdge.vertex1.y + otherEdge.yVelocity * time;
      double otherY2 = otherEdge.vertex2.y + otherEdge.yVelocity * time;

      // make sure the x values are in range of each other
      if (thisY2 >= otherY1 && thisY1 <= otherY2) {
        return time;
      }
      else {
        return Double.MAX_VALUE;
      }
    }

    private boolean bothHorizontalOrVertical(Edge edge1, Edge edge2) {
      if (edge1.direction == CollisionDirection.TOP) {
        return edge2.direction == CollisionDirection.TOP || edge2.direction == CollisionDirection.BOTTOM;
      }
      if (edge1.direction == CollisionDirection.BOTTOM) {
        return edge2.direction == CollisionDirection.TOP || edge2.direction == CollisionDirection.BOTTOM;
      }
      if (edge1.direction == CollisionDirection.RIGHT) {
        return edge2.direction == CollisionDirection.RIGHT || edge2.direction == CollisionDirection.LEFT;
      }
      if (edge1.direction == CollisionDirection.LEFT) {
        return edge2.direction == CollisionDirection.RIGHT || edge2.direction == CollisionDirection.LEFT;
      }

      return false;
    }

  }

}
