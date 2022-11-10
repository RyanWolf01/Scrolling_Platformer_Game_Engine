package ooga.model.entities;

import ooga.model.entities.data.EntityType;
import ooga.model.entities.data.InitialAttributes;

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

    /**
     *
     * @param xCoordinate new X coordinate to be set to
     */
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     *
     * @param yCoordinate new Y coordinate to be set to
     */
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
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
