package ooga.model.entities.characters;

import ooga.model.entities.Entity;
import ooga.model.Info;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.Mover;

public abstract class MainCharacter extends Entity implements Alive, Mover {
    private int lives;

    public MainCharacter(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
        super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
        this.lives = Integer.parseInt(entityInfo.get("lives"));
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
