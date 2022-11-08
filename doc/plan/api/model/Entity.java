/**
 * Everything that is displayed on the screen will be an Entity.
 * Various other interfaces will extend this entity. The very basics
 * are that every entity will have an X and Y defined in the Model and later displayed in the View.
 * They can also be collided with. Different Entity types will have different Collision rules for each type.
 */
public interface Entity {
    public int getXCoordinate();
    public int getYCoordinate();

    /**
     * When the Entity is hit on each by some other entity, what kind of collision is returned.
     * Collision might be an Enum or something.
     */
    public Collision onTopCollision();
    public Collsion onLeftCollision();
    public Collision onRightCollision();
    public Collision onBottomCollision();

    /**
     * Returns information about what type of entity this Entity is (i.e. Mario, a goomba, a
     * platform, etc.) along with what types of state information that these entities might hold
     * (e.g. the powerup that Mario has)
     */
    StateInformation getStateInformation();
}