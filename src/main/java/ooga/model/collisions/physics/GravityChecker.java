package ooga.model.collisions.physics;

import java.util.ResourceBundle;
import ooga.model.entities.Entity;
import ooga.model.entities.ImmutableEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GravityChecker {

  Logger LOG = LogManager.getLogger(GravityChecker.class);

  ResourceBundle entityInfoResources = ResourceBundle.getBundle("properties/entityInfo");

  private final String STATIC_PLATFORM;
  private final String TYPE;

  /**
   * Gravity Calculator checks if Entity is in the air
   */
  public GravityChecker(){

    try{
      STATIC_PLATFORM = entityInfoResources.getString("default_static_platform");
    } catch(NullPointerException exception){
      LOG.error("no value provided for default static platform in resources");
      throw exception;
    }

    try{
      TYPE = entityInfoResources.getString("type");
    } catch(NullPointerException exception){
      LOG.error("no value provided for type in resources");
      throw exception;
    }

  }

  /**
   * calculates if an Entity is in the Air
   * @return boolean
   */
  public boolean checkInAir(Entity entity){

    if(!entity.getMyCurrentCollisions().hasCollisions())
      return true;

    ImmutableEntity collided = entity.getMyCurrentCollisions().iterator().next();
    CollisionPhysicsInfo collisionPhysicsInfo = entity.getMyCurrentCollisions().get(collided);

    String entityType;
    try{
      entityType = collided.getImmutableEntityInfo().get(TYPE);
    }catch (NullPointerException exception){
      LOG.error("no type in entity info");
      throw exception;
    }

    if(entityInfoResources.containsKey(entityType)){
      entityType = entityInfoResources.getString(entityType);
    }

    if(entityType.equals(STATIC_PLATFORM) &&
        collisionPhysicsInfo.getCollisionDirection() == CollisionDirection.BOTTOM)
      return false;

    return true;
  }

}
