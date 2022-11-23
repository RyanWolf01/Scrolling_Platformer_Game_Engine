package ooga.model.collisions.physics;

import ooga.model.entities.Entity;

public class PhysicsCalculator {

  /**
   * This will enact the rules on the first entity, Entity a
   *
   * @param a the first entity, to be acted on
   * @param b the second entity, that is collided with
   */
  public CollisionPhysicsInfo calculatePhysics(Entity a, Entity b) {
    return new CollisionPhysicsInfo(true, checkDirection(a, b));
  }

  /**
   * Method that uses the components of each entity to figure out if there has been a collision and,
   * if so, on which side of Entity a did the collision happen
   *
   * @param a first entity, which we mostly care about
   * @param b second entity, which we don't care so mz32zX Cuch about
   * @return null if no collision, direction if its a real collision
   */
  private CollisionDirection checkDirection(Entity a, Entity b) {
    double aXCoord = a.getXCoordinate();
    double bXCoord = b.getXCoordinate();
    double aYCoord = a.getYCoordinate();
    double bYCoord = b.getYCoordinate();
    double aWidth = a.getWidth();
    double bWidth = b.getWidth();
    double aHeight = a.getHeight();
    double bHeight = b.getHeight();

    boolean inXRange = false;
    boolean inYRange = false;

    if ((aXCoord + aWidth) >= bXCoord && (aXCoord < bXCoord + bWidth)) {
      inXRange = true;
    }

    if ((aYCoord + aHeight) >= bYCoord && (aYCoord < bYCoord + bHeight)) {
      inYRange = true;
    }

    if (!(inXRange && inYRange)) {
      return null;
    }

    if ((aXCoord < bXCoord) && (aYCoord + aHeight > bYCoord)) {
      return CollisionDirection.RIGHT;
    }

    if ((aXCoord > bXCoord) && (aYCoord + aHeight > bYCoord)) {
      return CollisionDirection.LEFT;
    }

    if ((aYCoord < bYCoord) && (aXCoord < bXCoord + bWidth)) {
      return CollisionDirection.TOP;
    }

    if ((aYCoord > bYCoord) && (aXCoord < bXCoord + bWidth)) {
      return CollisionDirection.BOTTOM;
    }

    return null;
  }

  private CollisionDirection checkDirection2(Entity a, Entity b) {
    return null;
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
      if (!bothHorizontalOrVertical(this, otherEdge)) {
        return false;
      }

      if (this.direction == CollisionDirection.TOP || this.direction == CollisionDirection.BOTTOM) {
        return willIntersectTopOrBottom(otherEdge);
      }
      else {
        return willIntersectLeftOrRight(otherEdge);
      }
    }

    // TODO: consolidate willIntersectTopOrBottom and willIntersectLeftOrRight into one method
    private boolean willIntersectTopOrBottom(Edge otherEdge) {
      // Calculate time for x values to intersect
      double denominator = (otherEdge.yVelocity - this.yVelocity);
      if (denominator == 0) {
        return false;
      }
      double time = (this.vertex1.y - otherEdge.vertex1.y) / denominator;

      // get the x values at this time
      double thisX1 = this.vertex1.x + this.xVelocity * time;
      double thisX2 = this.vertex2.x + this.xVelocity * time;
      double otherX1 = otherEdge.vertex1.x + otherEdge.xVelocity * time;
      double otherX2 = otherEdge.vertex2.x + otherEdge.xVelocity * time;

      // make sure the x values are in range of each other
      return (thisX2 >= otherX1 && thisX1 <= otherX2);
    }

    private boolean willIntersectLeftOrRight(Edge otherEdge) {
      // Calculate time for x values to intersect
      double denominator = (otherEdge.xVelocity - this.xVelocity);
      if (denominator == 0) {
        return false;
      }
      double time = (this.vertex1.x - otherEdge.vertex1.x) / denominator;

      // get the x values at this time
      double thisY1 = this.vertex1.y + this.yVelocity * time;
      double thisY2 = this.vertex2.y + this.yVelocity * time;
      double otherY1 = otherEdge.vertex1.y + otherEdge.yVelocity * time;
      double otherY2 = otherEdge.vertex2.y + otherEdge.yVelocity * time;

      // make sure the x values are in range of each other
      return (thisY2 >= otherY1 && thisY1 <= otherY2);
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
