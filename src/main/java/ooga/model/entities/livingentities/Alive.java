package ooga.model.entities.livingentities;

import java.util.ResourceBundle;
import ooga.model.entities.info.ImmutableInfo;


/**
 * The Alive interface is used to distinguish between an entity that holds a concept of being alive (i.e. being
 * able to be destroyed) and one whose existence can never be altered.
 * Implement changeLives in subclass to guard how an entity can change its lives
 */
public interface Alive {

  /**
   * Returns number of lives of the current entity is alive. Extended by other
   * interfaces.
   */
  int getLives();

  /**
   * This method should perform all actions necessary to kill the entity. This is specific to a given entity, but
   * for Mario this may include setting its velocities to 0 and disabling abilities.
   */
  void kill();

  /**
   * Either increases or decreases lives of entity
   */
  void changeLives(int changeInLives);

  /**
   * gets initial lives from entity info
   * @param entityInfo entity info
   * @return lives
   */
  default int setInitialLives(ImmutableInfo entityInfo){
    ResourceBundle defaultAttributesProperties = ResourceBundle.getBundle("properties/defaultAttributes");
    int lives = 0;
    if(entityInfo.hasKey("lives")){
      try{
        lives = Integer.parseInt(entityInfo.get("lives"));
      }
      catch(NumberFormatException exception){
        lives = Integer.parseInt(defaultAttributesProperties.getString("lives"));
        throw exception;
      }
    }
    return lives;
  }


}
