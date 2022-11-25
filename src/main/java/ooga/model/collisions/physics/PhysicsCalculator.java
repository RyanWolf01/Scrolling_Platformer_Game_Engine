package ooga.model.collisions.physics;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.Entity;

public class PhysicsCalculator {

  // TODO: Potential bug -- what if you have two entities that shouldn't collide and you feed them
  // into here. If they do collide within trajectory of the entities but outside of the potential
  // distance they could've travelled to hit each other. You need to only allow things that are
  // within the velocity travelled...
  // Technically still works since you only call this function if you know a and b have collided
  // just make sure time is less than 1.0
  /**
   * This will enact the rules on the first entity, Entity a
   *
   * @param a the first entity, to be acted on
   * @param b the second entity, that is collided with
   */
  public CollisionPhysicsInfo calculatePhysics(Entity a, Entity b) {
    if (a.wasPreviouslyColliding(b)) {
      return new CollisionPhysicsInfo(true, a.getPreviousCollisionDirection(b));
    }
    return new CollisionPhysicsInfo(true, checkDirectionVelocityMethod(a, b));
//    return new CollisionPhysicsInfo(true, CollisionDirection.BOTTOM);
  }

  public CollisionDirection checkDirection(Entity a, Entity b) {
    CollisionDirection ret = checkDirectionVelocityMethod(a, b);
    if (ret == null) {
      ret = checkDirectionPositionMethod(a, b);
    }

    if (ret == null) {
      throw new RuntimeException("These objects did not collide");
    }
    else {
      return ret;
    }

  }

  private CollisionDirection checkDirectionPositionMethod(Entity a, Entity b) {
    return null;
  }

  private CollisionDirection checkDirectionVelocityMethod(Entity a, Entity b) {
    List<Edge> edgesA = new ArrayList<>();
    edgesA.add(getTopEdge(a));
    edgesA.add(getBottomEdge(a));
    edgesA.add(getLeftEdge(a));
    edgesA.add(getRightEdge(a));

    List<Edge> edgesB = new ArrayList<>();
    edgesB.add(getTopEdge(b));
    edgesB.add(getBottomEdge(b));
    edgesB.add(getLeftEdge(b));
    edgesB.add(getRightEdge(b));

    double minTime = Double.MAX_VALUE;
    CollisionDirection minTimeDirection = null;

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

    if (minTimeDirection == null) {
      throw new RuntimeException("These objects did not collide");
    }
    else {
      double moveBackX = a.getXVelocity() - (a.getXVelocity() * minTime);
      double moveBackY = a.getYVelocity() - (a.getYVelocity() * minTime);
      if (minTimeDirection == CollisionDirection.BOTTOM) {
        a.setYCoordinate(a.getYCoordinate() - moveBackY - 0.25);
      }
      else if (minTimeDirection == CollisionDirection.TOP) {
        a.setYCoordinate(a.getYCoordinate() - moveBackY + 0.25);
      }
      else if (minTimeDirection == CollisionDirection.LEFT) {
        a.setXCoordinate(a.getXCoordinate() - moveBackX + 0.25);
      }
      else if (minTimeDirection == CollisionDirection.RIGHT) {
        a.setXCoordinate(a.getXCoordinate() - moveBackX - 0.25);
      }
      return minTimeDirection;
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

    // TODO: Let this account for rounding errors (trying to compare 3.0 to 3.00000000001 and getting
    //  false when checking if they're equal)
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
