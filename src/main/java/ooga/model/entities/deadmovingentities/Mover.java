package ooga.model.entities.deadmovingentities;

/**
 * Implement move() in concrete classes to ensure guarding which directions an object can move
 */
public interface Mover extends MoverBehavior {

    /**
     *
     * @return version of MoverData
     */
    MoverData getMoverData();

    /**
     *
     * @param basicMoverBehavior new mover behavior
     */
    void setMoverBehavior(BasicMoverBehavior basicMoverBehavior);

    /**
     *
     * @return boolean if Mover is in air
     */
    boolean isInAir();

    boolean isHittingLeftOfPlatform();
    boolean isHittingRightOfPlatform();

    default boolean canMoveLeft() {
        return !isHittingLeftOfPlatform();
    }
    default boolean canMoveRight() {
        return !isHittingRightOfPlatform();
    }
    default boolean canMoveUp() {
        return !isInAir();
    }
    default boolean canMoveDown() {
        return isInAir();
    }

}
