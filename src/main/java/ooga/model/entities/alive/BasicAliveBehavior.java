package ooga.model.entities.alive;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MiscellaneousPropertiesException;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicAliveBehavior implements AliveBehavior{

  private int lives;
  private String LIVES_KEY;

  /**
   * make initial alive behavior
   * @param entityInfo used to set initialize lives
   */
  public BasicAliveBehavior(ImmutableInfo entityInfo){
    ResourceBundle entityInfoProperties = ResourceBundle.getBundle("properties/entityInfo");
    try{
      LIVES_KEY = entityInfoProperties.getString("lives_key");
    }catch(NullPointerException exception){
      throw new MiscellaneousPropertiesException("lives_key_missing");
    }

    lives = setInitialLives(entityInfo);

  }

  /**
   * make new Alive Behavior.
   * @param oldAliveBehavior old alive behavior used to set lives
   */
  public BasicAliveBehavior(ImmutableAliveBehavior oldAliveBehavior){
    lives = oldAliveBehavior.getLives();
  }

  /**
   * gets initial lives from entity info
   * @param entityInfo entity info
   * @return lives
   */
  private int setInitialLives(ImmutableInfo entityInfo){

    Logger LOG = LogManager.getLogger(Alive.class);

    ResourceBundle defaultAttributesProperties = ResourceBundle.getBundle("properties/defaultAttributes");

    int lives;

    try {

      lives = Integer.parseInt(entityInfo.get(LIVES_KEY));

    } catch(NumberFormatException | NullPointerException exception){
      LOG.error("lives formatted incorrectly in entity info");
      // try to get default value from properties
      try{
        lives = Integer.parseInt(defaultAttributesProperties.getString(LIVES_KEY));
      } catch(NumberFormatException propertiesException){
        LOG.error("lives formatted incorrectly in properties file");
        throw propertiesException;
      }
    }

    return lives;
  }

  /**
   * Returns number of lives of the current entity is alive.
   */
  public int getLives() {
    return lives;
  }

  /**
   * This method should perform all actions necessary to kill the entity. This is specific to a
   * given entity, but for Mario this may include setting its velocities to 0 and disabling
   * abilities.
   */
  public void kill() {
    changeLives(-1);
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  /**
   * allow characters to set their lives. Make sure lives cannot be negative
   *
   * @param lives value to which lives will now be set
   */
  private void setLives(int lives) {
    if (lives <= 0) {
      this.lives = 0;
    } else {
      this.lives = lives;
    }
  }



}
