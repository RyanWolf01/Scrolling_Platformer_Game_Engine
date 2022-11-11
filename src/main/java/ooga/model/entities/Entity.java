package ooga.model.entities;

import ooga.model.ImmutableInfo;
import ooga.model.Info;
import ooga.model.entities.data.EntityType;
import ooga.model.entities.data.InitialAttributes;

/**
 * Everything that can be represented in the game is an Entity of some type.
 * The one thing they all have in common is that they need to have some X and Y location.
 * They might also all need to have some size.
 */
public abstract class Entity {
    private double xCoordinate;
    private double yCoordinate;
    private double length;
    private double width;
    private InitialAttributes attributes;
    private Info entityInfo;

    public Entity(InitialAttributes attributes, Info entityInfo, double length, double width){
        this.attributes = attributes;
        xCoordinate = attributes.initialXCoordinate();
        yCoordinate = attributes.initialYCoordinate();
        this.entityInfo = entityInfo;

    }

    public double getXCoordinate(){
        return xCoordinate;
    }
    public double getYCoordinate(){
        return yCoordinate;
    }

    /**
     *
     * @param xCoordinate new X coordinate to be set to
     */
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     *
     * @param yCoordinate new Y coordinate to be set to
     */
    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getLength(){
        return length;
    }

    public double getWidth(){
        return width;
    }

    // TODO fix this method below
//    /**
//     * @return what type of entity the current entity is
//     */
//    public EntityType getEntityType(){
//        return entityInfo.;
//    }


    public ImmutableInfo getImmutableEntityInfo() {
        return entityInfo;
    };

}
