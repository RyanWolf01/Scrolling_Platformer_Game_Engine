package ooga.model;

public abstract class Entity {
    private EntityAttributes attributes;

    public int getXCoordinate(){
        return attributes.XCoordinate();
    }

    public int getYCoordinate(){
        return attributes.YCoordinate();
    }
}
