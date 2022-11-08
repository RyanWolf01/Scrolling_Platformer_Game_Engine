package ooga.model;

/**
 * Everything that can be represented in the game is an Entity of some type.
 * The one thing they all have in common is that they need to have some X and Y location.
 * They might also all need to have some size.
 */
public interface Entity {

    int getXCoordinate();
    int getYCoordinate();
}
