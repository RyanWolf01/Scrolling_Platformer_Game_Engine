package ooga.model.entities.deadmovingentities;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoverData implements ImmutableMoverData {

  private static final Logger LOG = LogManager.getLogger(MoverData.class);
  private final int SCREEN_SIZE;
  private int rightActionVelocity;
  private int leftActionVelocity;
  private int downwardActionVelocity;
  private int upwardActionVelocity;

  private final String RIGHT_VELOCITY_KEY;
  private final String LEFT_VELOCITY_KEY;
  private final String UPWARD_VELOCITY_KEY;
  private final String DOWNWARD_VELOCITY_KEY;

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
      LOG.error("screen size from properties file formatted incorrectly");
      throw exception;
    }

    SCREEN_SIZE = tempScreenSize;

    try{
      RIGHT_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("right_velocity_key"));
      LEFT_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("left_velocity_key"));
      DOWNWARD_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("downward_velocity_key"));
      UPWARD_VELOCITY_KEY = (ResourceBundle.getBundle("properties/movement").getString("upward_velocity_key"));
    }
    catch(NumberFormatException propertiesException){
      LOG.error("incorrect velocity key format in properties file");
      throw propertiesException;
    }

    initializeVelocities(entityInfo);

  }

  private void initializeVelocities(ImmutableInfo entityInfo){

    ResourceBundle resources = ResourceBundle.getBundle("properties/movement");
    parseVelocity(entityInfo, RIGHT_VELOCITY_KEY);
    parseVelocity(entityInfo, LEFT_VELOCITY_KEY);
    parseVelocity(entityInfo, UPWARD_VELOCITY_KEY);
    parseVelocity(entityInfo, DOWNWARD_VELOCITY_KEY);

  }

  private void parseVelocity(ImmutableInfo entityInfo, String key){

    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(entityInfo.get(key));
    }catch(NumberFormatException | NullPointerException exception){
      LOG.error("incorrect velocity format in entity info");
      try{
        tempVelocity = Integer.parseInt(
            ResourceBundle.getBundle("properties/movement").getString(key));
      }
      catch(NumberFormatException propertiesException){
        LOG.error("incorrect velocity format in properties file");
        throw propertiesException;
      }
    }

    Class thisClass = this.getClass();
    Field[] thisClassFields = thisClass.getDeclaredFields();
    try {
      for(Field field : thisClassFields) {
        field.setAccessible(true);
        if(field.getName().equals(key))
          field.set(this, tempVelocity);
      }
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * get velocity to use on a right action
   * @return right action velocity
   */
  public int getRightActionVelocity() {
    return rightActionVelocity;
  }

  public void setRightActionVelocity(int rightActionVelocity) {
    this.rightActionVelocity = rightActionVelocity;
  }

  /**
   * get velocity to use on a left action
   * @return left action velocity
   */
  public int getLeftActionVelocity() {
    return leftActionVelocity;
  }

  public void setLeftActionVelocity(int leftActionVelocity) {
    this.leftActionVelocity = leftActionVelocity;
  }

  /**
   * get velocity to use on a downward action
   * @return downward action velocity
   */
  public int getDownwardActionVelocity() {
    return downwardActionVelocity;
  }

  public void setDownwardActionVelocity(int downwardActionVelocity) {
    this.downwardActionVelocity = downwardActionVelocity;
  }

  /**
   * get velocity to use on an upward action
   * @return upward action velocity
   */
  public int getUpwardActionVelocity() {
    return upwardActionVelocity;
  }

  public void setUpwardActionVelocity(int upwardActionVelocity) {
    this.upwardActionVelocity = upwardActionVelocity;
  }
}
