package ooga.model.entities;

import ooga.model.entities.alive.Liver;
import ooga.model.entities.data.InitialAttributes;
import ooga.model.entities.movement.HorizontalMover;
import ooga.model.entities.movement.VerticalMover;

public class MainCharacter extends Entity implements HorizontalMover, VerticalMover, Liver {
    private int xVelocity;
    private int yVelocity;
    private int lives;

    public MainCharacter(InitialAttributes attributes) {
        super(attributes);
    }

    @Override
    public void incrementXVelocity(int change) {
        xVelocity = xVelocity+change;
    }

    @Override
    public int getXVelocity() {
        return xVelocity;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void incrementLives() {
        lives++;
    }

    @Override
    public void kill() {
        lives--;
    }

    @Override
    public void move() {
        incrementXCoordinate(xVelocity);
        incrementYCoordinate(yVelocity);
    }

    @Override
    public void incrementYVelocity(int change) {
        yVelocity += change;
    }

    @Override
    public int getYVelocity() {
        return yVelocity;
    }
}
