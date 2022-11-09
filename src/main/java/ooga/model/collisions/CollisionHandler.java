package ooga.model.collisions;

import ooga.model.entities.Entity;

/**
 * This will make sure the correct thing happens when two entities collide based on the predefined rules
 */
public class CollisionHandler {

    /**
     * This will enact the rules on the first entity, Entity a
     * @param a the first entity, to be acted on
     * @param b the second entity, that is collided with
     */
    public void handleOnFirst(Entity a, Entity b){
        a.getEntityType();
        b.getEntityType();
    }
}
