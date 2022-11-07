/**
 * Everything that is displayed on the screen will be an Entity.
 * Various other interfaces will extend this entity. The very basics
 * are that every entity will have an X and Y defined in the Model and later displayed in the View.
 * They can also be collided with.
 */
public interface Entity {
    public int getXCoordinate();
    public int getYCoordinate();

    /**
     * When the Entity is hit on each by some other entity, what kind of collision is returned.
     * Collision might be an Enum or something
     */
    public Collision onTopCollision();
    public Collsion onLeftCollision();
    public Collision onRightCollision();
    public Collision onBottomCollision();
}