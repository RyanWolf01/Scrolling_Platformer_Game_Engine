package ooga.model.entities.characters;

import ooga.model.ImmutableInfo;
import ooga.model.Info;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.collisions.CollisionPhysicsInfo;
import ooga.model.entities.Entity;
import ooga.model.entities.movement.Mover;

public class Mario extends MainCharacter implements Mover {

  public Mario(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
    super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * Implements Mover interface move method that changes object's position
   */
  @Override
  public void move() {
    setXCoordinate(getXCoordinate() + getXVelocity());
    setYCoordinate(getYCoordinate() + getYVelocity());
  }

  /**
   * Implements method in Alive interface that changes object's lives
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   * @param other
   */
  @Override
  public void onCollision(Entity other, CollisionPhysicsInfo physicsInfo){
//    ImmutableInfo entityAInfo = other.getImmutableEntityInfo();
//    AliveAction action = getPostCollisionAction(entityAInfo, entityBInfo, collisionPhysicsInfo);
  }


}
