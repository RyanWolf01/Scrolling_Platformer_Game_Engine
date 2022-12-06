package ooga.model.entities.livingentities.movingentities;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.deadmovingentities.MovingEntity;
import ooga.model.entities.info.Info;
import ooga.model.entities.livingentities.Alive;
import ooga.model.entities.livingentities.AliveBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingCharacter extends MovingEntity implements Alive {

  private static final Logger LOG = LogManager.getLogger(MovingCharacter.class);
  private AliveBehavior aliveBehavior;

  /**
   * Moving Character has lives and can move.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MovingCharacter(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    aliveBehavior = new AliveBehavior(entityInfo);
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    aliveBehavior.changeLives(changeInLives);
  }

  /**
   * Returns number of lives of the current entity is alive.
   */
  @Override
  public int getLives() {
    return aliveBehavior.getLives();
  }

  /**
   * This method should perform all actions necessary to kill the entity.
   */
  @Override
  public void kill() {
    aliveBehavior.kill();
  }

}
