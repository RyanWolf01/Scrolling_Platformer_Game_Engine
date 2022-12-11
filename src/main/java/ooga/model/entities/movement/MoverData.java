package ooga.model.entities.movement;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoverData implements ImmutableMoverData {

  private static final Logger LOG = LogManager.getLogger(MoverData.class);
  private final int SCREEN_SIZE;
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
    int tempScreenSize;
    try{
      tempScreenSize = Integer.parseInt(
          ResourceBundle.getBundle("properties/view").getString("screen_size"));
    } catch(NumberFormatException exception){
      throw new MiscellaneousPropertiesException("screen size from properties file formatted incorrectly", exception);
    }

    SCREEN_SIZE = tempScreenSize;

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

  private void initializeVelocities(ImmutableInfo entityInfo){

    ResourceBundle resources = ResourceBundle.getBundle("properties/movement");
    parseVelocity(entityInfo, RIGHT_VELOCITY_KEY);
    parseVelocity(entityInfo, LEFT_VELOCITY_KEY);
    parseVelocity(entityInfo, UPWARD_VELOCITY_KEY);
    parseVelocity(entityInfo, DOWNWARD_VELOCITY_KEY);
    parseVelocity(entityInfo, GRAVITY_VELOCITY_KEY);
  }

  private void parseVelocity(ImmutableInfo entityInfo, String key){

    double tempVelocity;
    try{
      tempVelocity = Double.parseDouble(entityInfo.get(key));
    }catch(NumberFormatException | NullPointerException exception){
      LOG.error("incorrect velocity format in entity info");
      try{
        tempVelocity = Double.parseDouble(
            ResourceBundle.getBundle("properties/movement").getString(key));
      }
      catch(NumberFormatException propertiesException){
        throw new MovementDataException("incorrect velocity format in properties file", propertiesException);
      }
    }

    Class thisClass = this.getClass();
    Field[] thisClassFields = thisClass.getDeclaredFields();
    boolean foundField = false;
    try {
      for(Field field : thisClassFields) {
        field.setAccessible(true);
        if(field.getName().equals(key)) {
          field.set(this, tempVelocity);
          foundField = true;
          break;
        }
      }
    } catch (IllegalAccessException e) {
      throw new MovementDataException("error using reflection in MoverData", e);
    }

    if(!foundField){
      throw new MovementDataException("couldn't find one of the velocities while parsing in MoverData");
    }
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
      throw new MovementDataException("tried to change right action velocity to negative value");
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
      throw new MovementDataException("tried to change left action velocity to positive value");
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
      throw new MovementDataException("tried to change downward action velocity to negative value");
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
      throw new MovementDataException("tried to change upward action velocity to positive value");
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
      throw new MovementDataException("tried to change gravity velocity to negative value");
    }
    this.gravityVelocity = gravityVelocity;
  }
}
