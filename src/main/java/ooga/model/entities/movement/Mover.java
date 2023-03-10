package ooga.model.entities.movement;

/**
 * Implement move() in concrete classes to ensure guarding which directions an object can move
 */
public interface Mover extends MoverBehavior {

    /**
     * get original movement behavior
     */
    BasicMoverBehavior getOriginalMoverBehavior();

    /**
     *
     * @return version of MoverData
     */
    MoverData getMoverData();

    /**
     * reset mover data to original
     */
    void resetMoverData();

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
