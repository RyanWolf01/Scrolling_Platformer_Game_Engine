package ooga.model.entities.movement;

/**
 * Interface used for anything that moves in the Y direction
 */
public interface VerticalMover extends Mover {
    /**
     * Changes the mover's velocity along the Y axis by a given constant
     * @param change how much to change the velocity by
     */
    void incrementYVelocity(int change);
}
