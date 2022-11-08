/**
 * Moving Entities are just like regular entities except they can move. Their movement is entirely defined by their velocities
 * and it is the main way it is affected at each step by other thing
 */
public interface MovingEntity extends Entity {
    int GRAVITATIONAL_CONSTANT = 16; // this constant (arbitrary) is how much the the y velocity is decremented by at every step
    int MAX_X_VELOCITY = 100; // maximum velocities in horizontal and vertical axes
    int MAX_Y_VELOCITY = 100;

    /**
     * Makes it so that the velocities can be changed from what it is noe
     * @param constant amount to change velocity by
     */
    public void incrementXVelocity(int constant);
    public void incrementYVelocity(int constant);

    /**
     * Stops entity from falling
     */
    public void stopYMovement();

}