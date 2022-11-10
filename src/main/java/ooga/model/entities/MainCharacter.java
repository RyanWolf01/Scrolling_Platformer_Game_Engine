package ooga.model.entities;

public class MainCharacter extends Entity implements HorizontalMover, VerticalMover, Liver{
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
