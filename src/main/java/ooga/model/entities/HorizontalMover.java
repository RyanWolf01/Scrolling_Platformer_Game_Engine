package ooga.model.entities;

/**
 * This is an interface that can be used for anything that moves in the horizontal dimension
 */
public interface HorizontalMover extends Entity {
    /**
     * Changes the mover's velocity along the X axis by a given constant
     * @param change how much to change the velocity by
     */
    void incrementXVelocity(int change);
    int getXVelocity();
}
