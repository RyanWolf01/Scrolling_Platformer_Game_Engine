/**
 * To get the gravity functionality to work, you would need to decrement the Y Velocity of the moving object.
 * The manin functionality is shown here as would happen in the Model step() class.
 */

public void step(){
    if(!entity.onPlatform){ // only decrement Y_velocity if the entity is floating
        ((MovingEntity) entity).incrementYVelocity(-1 * MovingEntity.GRAVITATIONAL_CONSTANT);
        // this would drop the y velocity of the entity by a certain amount, but in the incrementYVelocity method
        // there is a built in stoppage from exceeding 'terminal velocity'
    }
}