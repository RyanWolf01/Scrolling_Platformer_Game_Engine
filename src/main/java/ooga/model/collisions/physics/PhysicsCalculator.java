package ooga.model.collisions.physics;

import ooga.model.entities.CollidableEntity;
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


}
