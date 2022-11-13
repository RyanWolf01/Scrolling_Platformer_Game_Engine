package ooga.model.entities.characters;

import ooga.model.entities.Entity;
import ooga.model.Info;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.Mover;

public abstract class MainCharacter extends Entity implements Alive, Mover {
    private int lives;

    /**
     * Constructor for MainCharacter class that calls superclass Entity constructor
     * @param attributes initial conditions (x, y positions)
     * @param entityInfo information specific to subclass
     * @param length length of Mario (in y direction)
     * @param width width of Mario (in x direction)
     * @param lives initial lives
     */
    public MainCharacter(InitialAttributes attributes, Info entityInfo, double length, double width, int lives) {
        super(attributes, entityInfo, length, width);
        this.lives = lives;
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
