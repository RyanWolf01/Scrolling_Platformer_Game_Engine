package ooga.model.entities.characters;

import ooga.model.entities.Entity;
import ooga.model.entities.EntityInfo;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.data.InitialAttributes;

public abstract class MainCharacter extends Entity implements Alive {
    private int lives;

    public MainCharacter(InitialAttributes attributes, EntityInfo entityInfo, int lives) {
        super(attributes, entityInfo);
        this.lives = lives;
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
