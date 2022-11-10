package ooga.model.entities.characters;

import ooga.model.entities.Entity;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.Mover;

public abstract class MainCharacter extends Entity implements Alive {
    private int xVelocity;
    private int yVelocity;
    private int lives;

    public MainCharacter(InitialAttributes attributes) {
        super(attributes);
    }


    @Override
    public int getLives() {
        return lives;
    }


    @Override
    public void kill() {
        lives--;
    }

    /**
     * allow characters to set their lives
     * @param lives value to which lives will now be set
     */
    protected void setLives(int lives){
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
