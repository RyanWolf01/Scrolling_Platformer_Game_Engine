package ooga.model.entities.movement;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoverData implements ImmutableMoverData {

  private static final Logger LOG = LogManager.getLogger(MoverData.class);
  private double rightActionVelocity;
  private double leftActionVelocity;
  private double downwardActionVelocity;
  private double upwardActionVelocity;
  private double gravityVelocity;
  private final String RIGHT_VELOCITY_KEY;
  private final String LEFT_VELOCITY_KEY;
  private final String UPWARD_VELOCITY_KEY;
  private final String DOWNWARD_VELOCITY_KEY;
  private final String GRAVITY_VELOCITY_KEY;


  /**
   * Holds velocities to use in Mover Actions
   * @param entityInfo
   */
  public MoverData(ImmutableInfo entityInfo){

    try{
      RIGHT_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("right_velocity_key"));
      LEFT_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("left_velocity_key"));
      DOWNWARD_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("downward_velocity_key"));
      UPWARD_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("upward_velocity_key"));
      GRAVITY_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("gravity_velocity_key"));
    }
    catch(NumberFormatException propertiesException){
      throw new MovementDataException("incorrect velocity key format in properties file", propertiesException);
    }

    initializeVelocities(entityInfo);

  }

  /**
   * initialize all velocities from entity info or properties
   * @param entityInfo this entity's entity info
   */
  private void initializeVelocities(ImmutableInfo entityInfo){

    ResourceBundle resources = ResourceBundle.getBundle("properties/movement");
    initializeVelocity(entityInfo, RIGHT_VELOCITY_KEY);
    initializeVelocity(entityInfo, LEFT_VELOCITY_KEY);
    initializeVelocity(entityInfo, UPWARD_VELOCITY_KEY);
    initializeVelocity(entityInfo, DOWNWARD_VELOCITY_KEY);
    initializeVelocity(entityInfo, GRAVITY_VELOCITY_KEY);
  }

  /**
   * Uses reflection to assign values to attributes. Used properties if can't find value in entity info
   * @param entityInfo this entity's entity info
   * @param key the velocity currently being parsed
   */
  private void initializeVelocity(ImmutableInfo entityInfo, String key){

    double velocity = parseVelocity(entityInfo, key);

    boolean foundField = assignVelocityToField(key, velocity);

    if(!foundField){
      throw new MovementDataException("velocity_parse_error");
    }
  }

  /**
   * helper method to assign parsed velocity to field using reflection
   * @param key velocity key
   * @param velocity velocity
   * @return boolean representing if field was found
   */
  private boolean assignVelocityToField(String key, double velocity) {
    Class thisClass = this.getClass();
    Field[] thisClassFields = thisClass.getDeclaredFields();
    boolean foundField = false;
    try {
      for(Field field : thisClassFields) {
        field.setAccessible(true);
        if(field.getName().equals(key)) {
          field.set(this, velocity);
          foundField = true;
          break;
        }
      }
    } catch (IllegalAccessException e) {
      throw new MovementDataException("reflection_error", e);
    }
    return foundField;
  }

  /**
   * helper method to get velocity from entity info or properties
   * @param entityInfo entity info
   * @param key current velocity
   * @return velocity
   */
  private double parseVelocity(ImmutableInfo entityInfo, String key) {
    double velocity;
    try{
      velocity = Double.parseDouble(entityInfo.get(key));
    }catch(NumberFormatException | NullPointerException exception){
      LOG.error("incorrect velocity format in entity info");
      try{
        velocity = Double.parseDouble(
            ResourceBundle.getBundle("properties/movement").getString(key));
      }
      catch(NumberFormatException propertiesException){
        throw new MovementDataException("velocity_parse_error", propertiesException);
      }
    }
    return velocity;
  }

  /**
   * get velocity to use on a right action
   * @return right action velocity
   */
  public double getRightActionVelocity() {
    return rightActionVelocity;
  }

  /**
   * set right action velocity
   * @param rightActionVelocity
   */
  public void setRightActionVelocity(double rightActionVelocity) {
    if(rightActionVelocity < 0){
      throw new MovementDataException("bad_set_velocity_error");
    }
    this.rightActionVelocity = rightActionVelocity;
  }

  /**
   * get velocity to use on a left action
   * @return left action velocity
   */
  public double getLeftActionVelocity() {
    return leftActionVelocity;
  }

  /**
   * set left action velocity
   * @param leftActionVelocity
   */
  public void setLeftActionVelocity(double leftActionVelocity) {
    if(leftActionVelocity > 0){
      throw new MovementDataException("bad_set_velocity_error");
    }
    this.leftActionVelocity = leftActionVelocity;
  }

  /**
   * get velocity to use on a downward action
   * @return downward action velocity
   */
  public double getDownwardActionVelocity() {
    return downwardActionVelocity;
  }

  /**
   * set downward action velocity
   * @param downwardActionVelocity
   */
  public void setDownwardActionVelocity(double downwardActionVelocity) {
    if(downwardActionVelocity < 0){
      throw new MovementDataException("bad_set_velocity_error");
    }
    this.downwardActionVelocity = downwardActionVelocity;
  }

  /**
   * get velocity to use on an upward action
   * @return upward action velocity
   */
  public double getUpwardActionVelocity() {
    return upwardActionVelocity;
  }

  /**
   * set upward action velocity
   * @param upwardActionVelocity
   */
  public void setUpwardActionVelocity(double upwardActionVelocity) {
    if(upwardActionVelocity > 0){
      throw new MovementDataException("bad_set_velocity_error");
    }
    this.upwardActionVelocity = upwardActionVelocity;
  }

  /**
   * get velocity to use on a gravity action
   * @return gravity action velocity
   */
  public double getGravityVelocity() {
    return gravityVelocity;
  }

  /**
   * set gravity velocity
   * @param gravityVelocity
   */
  public void setGravityVelocity(double gravityVelocity) {
    if(gravityVelocity < 0){
      throw new MovementDataException("bad_set_velocity_error");
    }
    this.gravityVelocity = gravityVelocity;
  }
}
