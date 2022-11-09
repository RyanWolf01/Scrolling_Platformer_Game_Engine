package ooga.model.entities;

/**
 * The main use of this enum is for the collisions. Based on the type of the entity different
 * things will happen to different things.
 */
public enum EntityType {
    MAIN_CHARACTER,
    BOUNCY_PLATFORM,
    BORING_PLATFORM,
    BABY_ENEMY,
    BIG_BOI_ENEMY;

    /**
     * This stuff will probably be helpful for reflection when doing collisions
     */
//    private Class clazz;
//    private EntityType(Class clazz) {
//        this.clazz = clazz;
//    }
//
//    public Class getClazz(){
//        return clazz.getClass();
//    }

}