package ooga.model.entities.movement;

/**
 * Implement move() in concrete classes to ensure guarding which directions an object can move
 */
public interface Mover {
    /**
     * This will move the Mover entity by its current velocity in whichever direction it should
     */
    void move();

    /**
     * @param changeYVelocity changes Mover's y velocity and updates position
     * @param changeXVelocity changes Mover's x velocity and updates position
     */
    void changeVelocities(double changeXVelocity, double changeYVelocity);

    /**
     * Reset velocities
     *
     * @param resetX tells if should reset xVelocity
     * @param resetY tells if should reset yVelocity
     *
     */
    void resetVelocities(boolean resetX, boolean resetY);

    /**
     *
     * @return double x velocity
     */
    public double getXVelocity();

    /**
     *
     * @return double y velocity
     */
    public double getYVelocity();



}
