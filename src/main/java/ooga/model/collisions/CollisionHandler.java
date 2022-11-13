package ooga.model.collisions;

import ooga.model.collisions.data.CollisionDirection;
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
//        a.getEntityType();
//        b.getEntityType();
    }

    /**
     * Method that uses the components of each entity to figure out if
     * there has been a collision and, if so, on which side of Entity a
     * did the collision happen
     * @param a first entity, which we mostly care about
     * @param b second entity, which we don't care so much about
     * @return null if no collision, direction if its a real collision
     */
    private CollisionDirection checkCollision(Entity a, Entity b){
//        int aXCoord = a.getXCoordinate();
//        int bXCoord = b.getXCoordinate();
//        int aYCoord = a.getYCoordinate();
//        int bYCoord = b.getYCoordinate();
//        int aLen = a.getLength();
//        int bLen = b.getLength();
//        int aWidth = a.getWidth();
//        int bWidth = b.getWidth();
//
//        boolean inXRange = false;
//        boolean inYRange = false;
//
//        if((aXCoord+aLen) >= bXCoord && (aXCoord < bXCoord+bLen)){
//            inXRange = true;
//        }
//
//        if((aYCoord+aWidth) >= bYCoord && (aYCoord < bYCoord+bWidth)){
//            inYRange = true;
//        }
//
//        if(!(inXRange && inYRange)){
//            return null;
//        }
//
//        if((aXCoord < bXCoord) && (aYCoord+aWidth > bYCoord)){
//            return CollisionDirection.RIGHT;
//        }
//
//        if((aXCoord > bXCoord) && (aYCoord+aWidth > bYCoord)){
//            return CollisionDirection.LEFT;
//        }
//
//        if((aYCoord < bYCoord) && (aXCoord < bXCoord+bLen)){
//            return CollisionDirection.TOP;
//        }
//
//        if((aYCoord > bYCoord) && (aXCoord < bXCoord+bLen)){
//            return CollisionDirection.BOTTOM;
//        }
//
        return null;
    }
}
