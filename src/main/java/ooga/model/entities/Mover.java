package ooga.model.entities;

public interface Mover extends Entity{
    /**
     * This will move the Mover entity by its current velocity in whichever direction it should
     */
    void move();
}
