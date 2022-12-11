package ooga.model.entities.livingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;

public abstract class StaticCharacter extends CollidableEntity implements Alive {

  private BasicAliveBehavior basicAliveBehavior;

  /**
   * Static Entity has lives but can not move.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public StaticCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    basicAliveBehavior = new BasicAliveBehavior(entityInfo);
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    basicAliveBehavior.changeLives(changeInLives);
  }

  /**
   * Returns number of lives of the current entity is alive. Extended by other interfaces.
   */
  @Override
  public int getLives() {
    return basicAliveBehavior.getLives();
  }

  /**
   * This method should perform all actions necessary to kill the entity. This is specific to a
   * given entity, but for Mario this may include setting its velocities to 0 and disabling
   * abilities.
   */
  @Override
  public void kill() {
    basicAliveBehavior.kill();
  }

  /**
   * returns immutable version of alive behavior
   */
  @Override
  public ImmutableAliveBehavior getAliveBehavior(){
    return basicAliveBehavior;
  }

  /**
   * set new AliveBehavior
   * @param oldBasicAliveBehavior is old alive behavior
   */
  @Override
  public void setAliveBehavior(BasicAliveBehavior oldBasicAliveBehavior){
    this.basicAliveBehavior = oldBasicAliveBehavior;
  }


}
