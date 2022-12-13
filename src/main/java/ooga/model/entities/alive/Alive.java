package ooga.model.entities.alive;


/**
 * The Alive interface is used to distinguish between an entity that holds a concept of being alive (i.e. being
 * able to be destroyed) and one whose existence can never be altered.
 * Implement changeLives in subclass to guard how an entity can change its lives
 */
public interface Alive extends AliveBehavior {


  /**
   * returns immutable version of alive behavior
   */
  ImmutableAliveBehavior getAliveBehavior();

  /**
   * set new AliveBehavior
   * @param oldBasicAliveBehavior is old alive behavior
   */
  void setAliveBehavior(BasicAliveBehavior oldBasicAliveBehavior);



}
