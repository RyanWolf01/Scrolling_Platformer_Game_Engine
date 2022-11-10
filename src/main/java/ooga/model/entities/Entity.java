package ooga.model.entities;

/**
 * Everything that can be represented in the game is an Entity of some type.
 * The one thing they all have in common is that they need to have some X and Y location.
 * They might also all need to have some size.
 */
public abstract class Entity {
    private int xCoordinate;
    private int yCoordinate;
    private InitialAttributes attributes;

    public Entity(InitialAttributes attributes){
        this.attributes = attributes;
        xCoordinate = attributes.initialXCoordinate();
        yCoordinate = attributes.initialYCoordinate();
    }

    public int getXCoordinate(){
        return xCoordinate;
    }
    public int getYCoordinate(){
        return yCoordinate;
    }

    public void incrementXCoordinate(int velocity){
        xCoordinate += velocity;
    }

    public void incrementYCoordinate(int velocity){
        yCoordinate += velocity;
    }

    public int getLength(){
        return attributes.length();
    }

    public int getWidth(){
        return attributes.width();
    }

    /**
     * @return what type of entity the current entity is
     */
    public EntityType getEntityType(){
        return attributes.type();
    }
}
