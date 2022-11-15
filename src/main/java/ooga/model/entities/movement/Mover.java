package ooga.model.entities.movement;

/**
 * Implement in subclass to ensure guarding which directions an object can move
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
    void changeVelocities(int changeXVelocity, int changeYVelocity);
}
