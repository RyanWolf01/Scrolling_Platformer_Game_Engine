package ooga.model.collisions.physics;

import java.util.ArrayList;
import java.util.List;
import ooga.model.entities.Entity;

public class PhysicsCalculator {

  // TODO: Potential bug -- what if you have two entities that shouldn't collide and you feed them
  // into here. If they do collide within trajectory of the entities but outside of the potential
  // distance they could've travelled to hit each other. You need to only allow things that are
  // within the velocity travelled...
  // Technically still works since you only call this function if you know a and b have collided
  // just make sure time is less than 1.0

  // TODO: Another potential bug: If mario is wedged between two things that it collides with at the
  // same time, Mario will retain its original velocities after colliding with thing1 and this could
  // mess up collisions with thing2

  // The approach of moving Mario back to its original position right before impact doesn't really
  // work because if you don't want to do anything if two objects collide (pretend that they can
  // just move through each other and keep going), that won't work. They'll get stuck on each other,
  // and it will appear like Entity a is inside of Entity b, because a keeps moving down over and
  // over again inside of Entity b because its original velocity doesn't change.
  // This movement should only really be done after you've determined that things should stop when
  // hitting each other.

  // CollisionPhysics info can contain the number of times these things have sequentially hit each
  // other. For the first hit, we can move the things right outside of their spaces. Then, if they
  // hit again, we can use the previous CollisionDirection and allow the things to intersect inside
  // each other.

  // Also important to have CollisionPhysics contain the number of times things have sequentially
  // hit because what if you hit something that you want to move through, but do something every
  // time that thing is hit, like gain a point, you don't necessarily want to gain a point every
  // 1/60th of a second or every time a collision occurs while the thing is going through another
  // object. Only once during the sequence of that collision. But you might also want to continually
  // do something to an object that's being collided with but at a specific rate (e.g. if you're
  // colliding with water, you might want to decrement your oxygen level by 1 per 3 seconds). This
  // would require knowing how many times you've collided with the water using
  // collisioncheckrate and number of collisions.
  // What you can do is reset the position so things aren't techincally "colliding" after the first
  // collision, and if the things are colliding in the next round of checks, then don't do that/allow
  // the things to pass through. Will eliminate movement during 1 frame, which may be visible/look
  // funny and also mess up perfect trajectories of things...

  // Another bug -- what if you spawn A inside of B, then you'll throw an error because you can't
  // calculate where the collision comes from... Temporary (?) solution, add a PhysicsDirection.NONE
  // and in the collisionChart, add a criteria for colliding with platforms with direction of NONE.
  // Here, make it so that nothing is done when this happens. Because i guess in this situtation, if
  // something is teleporting into you, you. Want to use hierarchical collision charts so that you
  // can put the reaction to spawning inside other things under characteristics of Entity, rather
  // than having to redefine it for goomba, etc.

  // still deal with the issue of stacked platforms, hitting upper_platform before lower_platform
  // but handling both in the same sequence, so that you move thing inside the upper platform
  // afterwards since you move it outside of lower platform, which is within upper one.... i think
  /**
   * This will enact the rules on the first entity, Entity a
   *
   * @param a the first entity, to be acted on
   * @param b the second entity, that is collided with
   */
  public CollisionPhysicsInfo calculatePhysics(Entity a, Entity b) {
//    if (! entitiesAreColliding(a, b)) {
//      throw new RuntimeException("Improper usage of this method. Entities aren't colliding, and"
//          + " this method should only be called when a and b are known to be colliding.");
//    }

    CollisionPhysicsInfo info = new CollisionPhysicsInfo(true, 1, checkDirectionVelocityMethod(a, b));
    a.getMyCurrentCollisions().set(b, info);
    return info;
//    return new CollisionPhysicsInfo(true, CollisionDirection.BOTTOM);
  }

  /**
   * Assumes that a and b have already been colliding. TODO: Should check to make sure this is actually true
   * @param a
   * @param b
   * @param prevCollisionPhysicsInfo
   * @return
   */
  public CollisionPhysicsInfo calculatePhysics(Entity a, Entity b, CollisionPhysicsInfo prevCollisionPhysicsInfo) {
//    if (! entitiesAreColliding(a, b)) {
//      throw new RuntimeException("Improper usage of this method. Entities aren't colliding, and"
//          + " this method should only be called when a and b are known to be colliding.");
//    }

    prevCollisionPhysicsInfo.incrementNumConsecutiveCollisions();
    return prevCollisionPhysicsInfo;
  }

  public CollisionDirection checkDirection(Entity a, Entity b) {
    CollisionDirection ret = checkDirectionVelocityMethod(a, b);
    return ret;
//    if (ret == null) {
//      ret = checkDirectionPositionMethod(a, b);
//    }
//
//    if (ret == null) {
//      throw new RuntimeException("These objects did not collide");
//    }
//    else {
//      return ret;
//    }

  }

  private boolean entitiesAreColliding(Entity a, Entity b) {
    if (a.getXCoordinate() > b.getXCoordinate() + b.getWidth()) return false;
    if (a.getXCoordinate() + a.getWidth() < b.getXCoordinate()) return false;
    if (a.getYCoordinate() - a.getHeight() > b.getYCoordinate()) return false;
    if (a.getYCoordinate() < b.getYCoordinate() - a.getHeight()) return false;

    return true;
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
    if (minTimeDirection != CollisionDirection.NONE) {
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
    }

    return minTimeDirection;

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
