package ooga.model.entities.entitymodels;

import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.entities.info.ImmutableInfo;
import ooga.model.entities.info.Info;

/**
 * Everything that can be represented in the game is an Entity of some type.
 * The one thing they all have in common is that they need to have some X and Y location.
 * They might also all need to have some size.
 */
public abstract class Entity implements ImmutableEntity{
    private double xCoordinate;
    private double yCoordinate;
    private double height;
    private double width;
    private Info entityInfo;
    private InitialAttributes initialAttributes;

    public Entity(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo){
        this.xCoordinate = initialXCoordinate;
        this.yCoordinate = initialYCoordinate;
        this.height = height;
        this.width = width;
        this.entityInfo = entityInfo;
        initialAttributes = new InitialAttributes(initialXCoordinate, initialYCoordinate, height, width);
    }

    /**
     *
     * @return initial attributes (height, width, x, y)
     */
    public InitialAttributes getInitialAttributes() {
        return initialAttributes;
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

    /**
     * Need this getter in the PhysicsCalculator class
     * @return height of entity
     */
    public double getHeight(){
        return height;
    }

    /**
     * Set new height. Need this for power ups.
     */
    public void setHeight(double height){
        this.height = height;
    }

    /**
     * Set new width. Need this for power ups.
     */
    public void setWidth(double width){
        this.width = width;
    }

    /**
     * Need this getter in the PhysicsCalculator class
     * @return width of entity
     */
    public double getWidth(){
        return width;
    }

    public ImmutableInfo getImmutableEntityInfo() {
        return entityInfo;
    };

}
