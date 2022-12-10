package ooga.model.entities.livingentities;

import java.util.ResourceBundle;
import ooga.model.entities.deadmovingentities.MoverBehavior;
import ooga.model.entities.deadmovingentities.MoverData;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Alive interface is used to distinguish between an entity that holds a concept of being alive (i.e. being
 * able to be destroyed) and one whose existence can never be altered.
 * Implement changeLives in subclass to guard how an entity can change its lives
 */
public interface Alive {

  /**
   * Returns number of lives of the current entity is alive.
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
   * returns immutable version of alive behavior
   */
  ImmutableAliveBehavior getAliveBehavior();

  /**
   * set new AliveBehavior
   */
  void setAliveBehavior(AliveBehavior aliveBehavior);



}
