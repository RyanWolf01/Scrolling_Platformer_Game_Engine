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
   * @param b second entity, which we don't care so much about
   * @return null if no collision, direction if its a real collision
   */
  private CollisionDirection checkDirection(Entity a, Entity b) {
    double aXCoord = a.getXCoordinate();
    double bXCoord = b.getXCoordinate();
    double aYCoord = a.getYCoordinate();
    double bYCoord = b.getYCoordinate();
    double aLen = a.getHeight();
    double bLen = b.getHeight();
    double aWidth = a.getWidth();
    double bWidth = b.getWidth();

    boolean inXRange = false;
    boolean inYRange = false;

    if ((aXCoord + aLen) >= bXCoord && (aXCoord < bXCoord + bLen)) {
      inXRange = true;
    }

    if ((aYCoord + aWidth) >= bYCoord && (aYCoord < bYCoord + bWidth)) {
      inYRange = true;
    }

    if (!(inXRange && inYRange)) {
      return null;
    }

    if ((aXCoord < bXCoord) && (aYCoord + aWidth > bYCoord)) {
      return CollisionDirection.RIGHT;
    }

    if ((aXCoord > bXCoord) && (aYCoord + aWidth > bYCoord)) {
      return CollisionDirection.LEFT;
    }

    if ((aYCoord < bYCoord) && (aXCoord < bXCoord + bLen)) {
      return CollisionDirection.TOP;
    }

    if ((aYCoord > bYCoord) && (aXCoord < bXCoord + bLen)) {
      return CollisionDirection.BOTTOM;
    }

    return null;
  }


}
