package ooga.model.entities.entitymodels;

import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.physics.GravityChecker;
import ooga.model.entities.info.Info;
import ooga.model.entities.movement.AutomaticMoverBehavior;
import ooga.model.entities.movement.BasicMoverBehavior;
import ooga.model.entities.movement.MovementQueue;
import ooga.model.entities.movement.Mover;
import ooga.model.entities.movement.MoverData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MovingEntity extends CollidableEntity implements Mover {

  private static final Logger LOG = LogManager.getLogger(MovingEntity.class);

  private BasicMoverBehavior basicMoverBehavior;
  private MoverData moverData;

  /**
   * Moving Entity has no lives but can move. Constructor initializes screen size from Properties files.
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
  public MovingEntity(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo, MovementQueue movementQueue) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    this.moverData = new MoverData(entityInfo);

    initializeMoverBehavior(movementQueue);
  }

  /**
   * initialize mover behavior based on movement queue existence
   * @param movementQueue movement queue
   */
  private void initializeMoverBehavior(MovementQueue movementQueue){
    if(movementQueue == null || movementQueue.nextMove() == null)
      basicMoverBehavior = new BasicMoverBehavior();
    else
      basicMoverBehavior = new AutomaticMoverBehavior(movementQueue, this);
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    basicMoverBehavior.move();
    setXCoordinate(getXCoordinate() + basicMoverBehavior.getXVelocity());
    setYCoordinate(getYCoordinate() + basicMoverBehavior.getYVelocity());
  }

  /**
   * Implements Mover interface changeVelocities method that changes object's velocities
   */
  @Override
  public void changeVelocities(double changeXVelocity, double changeYVelocity) {
    basicMoverBehavior.changeVelocities(changeXVelocity, changeYVelocity);
  }

  /**
   * Reset velocities
   *
   * @param resetX tells if should reset xVelocity
   * @param resetY tells if should reset yVelocity
   *
   */
  @Override
  public void resetVelocities(boolean resetX, boolean resetY){
    basicMoverBehavior.resetVelocities(resetX, resetY);
  }

  /**
   * @return x velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getXVelocity() {
    return basicMoverBehavior.getXVelocity();
  }

  /**
   * @return y velocity, implements Mover method This getter is needed for some MoverAction classes,
   * and for subclasses
   */
  @Override
  public double getYVelocity() {
    return basicMoverBehavior.getYVelocity();
  }

  /**
   * returns if entity is in air
   * @return boolean
   */
  @Override
  public boolean isInAir(){
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkInAir(this);
  }

  @Override
  public boolean isHittingLeftOfPlatform() {
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkHittingLeftOfPlatform(this);
  }

  @Override
  public boolean isHittingRightOfPlatform() {
    GravityChecker gravityChecker = new GravityChecker();
    return gravityChecker.checkHittingRightOfPlatform(this);
  }

  /**
   *
   * @return immutable version of MoverData
   */
  @Override
  public MoverData getMoverData(){
    return moverData;
  }

  /**
   *
   * @param basicMoverBehavior new mover behavior
   */
  @Override
  public void setMoverBehavior(BasicMoverBehavior basicMoverBehavior){
    this.basicMoverBehavior = basicMoverBehavior;
  }

 @Override
 /**
  * reset mover data to original
  */
 public void resetMoverData(){
   moverData = new MoverData(getImmutableEntityInfo());
 }


}
