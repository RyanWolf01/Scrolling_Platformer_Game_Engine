package ooga.model.entities.movement;


public interface Mover {
    /**
     * This will move the Mover entity by its current velocity in whichever direction it should
     * @param changeYVelocity changes Mover's y velocity and updates position
     * @param changeXVelocity changes Mover's x velocity and updates position
     */
    void move(int changeXVelocity, int changeYVelocity);
}
