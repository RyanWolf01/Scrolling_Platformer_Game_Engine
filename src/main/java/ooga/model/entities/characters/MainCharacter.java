package ooga.model.entities.characters;

import ooga.model.collisions.Collidable;
import ooga.model.entities.Entity;
import ooga.model.Info;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

public abstract class MainCharacter extends Entity implements Alive, Mover, Collidable {
    private int lives;

    private double xVelocity;
    private double yVelocity;

    public MainCharacter(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
        super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
//        this.lives = Integer.parseInt(entityInfo.get("lives"));
    }

    /**
     * Implements Mover interface changeVelocities method that changes object's velocities
     */
    @Override
    public void changeVelocities(double changeXVelocity, double changeYVelocity){
        xVelocity += changeXVelocity;
        yVelocity += changeYVelocity;
    }

    /**
     *
     * @return x velocity, implements Mover method
     * This getter is needed for some MoverAction classes, and for subclasses
     */
    @Override
    public double getXVelocity(){
        return xVelocity;
    }

    /**
     *
     * @return y velocity, implements Mover method
     * This getter is needed for some MoverAction classes, and for subclasses
     */
    @Override
    public double getYVelocity(){
        return yVelocity;
    }

    /**
     * Returns number of lives of the current entity is alive. Extended by other
     * interfaces.
     */
    @Override
    public int getLives() {
        return lives;
    }

    /**
     * This method should perform all actions necessary to kill the entity. This is specific to a given entity, but
     * for Mario this may include setting its velocities to 0 and disabling abilities.
     */
    @Override
    public void kill() {
        lives--;
    }

    /**
     * allow characters to set their lives. Make sure lives cannot be negative
     * @param lives value to which lives will now be set
     */
    protected void setLives(int lives){
        if(lives <= 0)
            this.lives = 0;
        else
            this.lives = lives;
    }

    /**
     * allow characters to set their lives
     * @param lives value to which lives will now be set
     */
    protected void set(int lives){
        this.lives = lives;
    }

}
