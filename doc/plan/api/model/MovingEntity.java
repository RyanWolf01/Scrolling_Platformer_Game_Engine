/**
 * Moving Entities are just like regular entities except they can move.
 */
public interface MovingEntity extends Entity {
    int GRAVITATIONAL_CONSTANT = 16; // this constant (arbitrary) is how much the the y velocity is decremented by at every step
    int MAX_X_VELOCITY = 100; // maximum velocities in horizontal and vertical axes
    int MAX_Y_VELOCITY = 100;

    public void incrementXVelocity(int constant);
    public void incrementYVelocity(int constant);
}