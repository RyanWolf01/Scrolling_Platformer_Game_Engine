package ooga.model.entities.entitymodels;

import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.movement.MovementQueue;
import ooga.model.entities.info.Info;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.alive.BasicAliveBehavior;
import ooga.model.entities.alive.ImmutableAliveBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovingCharacter extends MovingEntity implements Alive {

  private static final Logger LOG = LogManager.getLogger(MovingCharacter.class);
  private BasicAliveBehavior basicAliveBehavior;

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
      Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo, movementQueue);
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
   * Returns number of lives of the current entity is alive.
   */
  @Override
  public int getLives() {
    return basicAliveBehavior.getLives();
  }

  /**
   * This method should perform all actions necessary to kill the entity.
   */
  @Override
  public void kill() {
    basicAliveBehavior.kill();
  }

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

  @Override
  protected int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }

}
