package ooga.model.collisions.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.entities.Entity;
import ooga.model.entities.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.info.Info;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
import org.junit.jupiter.api.Test;

public class PhysicsCalculatorTest {

  @Test
  public void checkRightCollision() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    Entity collider = new MainCharacter(new DefaultCollisionChart(), 50, 0, 100, 100, new EntityInfo("MARIO"));
    Entity collided = new StaticEntity(150, 0, 100, 50, new Info());

    CollisionPhysicsData res = physicsCalculator.calculatePhysicsData(collider, collided);
    assertEquals(CollisionDirection.RIGHT, res.getCollisionDirection());
    assertEquals(true, res.collisionIsFresh());
    assertEquals(1, res.getNumConsecutiveCollisions());
  }

  @Test
  public void checkLeftCollision() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    Entity collided = new MainCharacter(new DefaultCollisionChart(), 50, 0, 100, 50, new EntityInfo("MARIO"));
    Entity collider = new StaticEntity(150, 0, 100, 50, new Info());
    collider.setXCoordinate(100);

    CollisionPhysicsData res = physicsCalculator.calculatePhysicsData(collider, collided);
    assertEquals(CollisionDirection.LEFT, res.getCollisionDirection());
    assertEquals(true, res.collisionIsFresh());
    assertEquals(1, res.getNumConsecutiveCollisions());
  }

  @Test
  public void checkTopCollision() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    Entity collider = new MainCharacter(new DefaultCollisionChart(), 50, 100, 100, 50, new EntityInfo("MARIO"));
    Entity collided = new StaticEntity(50, 0, 100, 50, new Info());

    CollisionPhysicsData res = physicsCalculator.calculatePhysicsData(collider, collided);
    assertEquals(CollisionDirection.TOP, res.getCollisionDirection());
    assertEquals(true, res.collisionIsFresh());
    assertEquals(1, res.getNumConsecutiveCollisions());
  }

  @Test
  public void checkBottomCollision() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    Entity collider = new StaticEntity(50, 0, 100, 50, new Info());
    Entity collided = new MainCharacter(new DefaultCollisionChart(), 50, 100, 100, 50, new EntityInfo("MARIO"));

    CollisionPhysicsData res = physicsCalculator.calculatePhysicsData(collider, collided);
    assertEquals(CollisionDirection.BOTTOM, res.getCollisionDirection());
    assertEquals(true, res.collisionIsFresh());
    assertEquals(1, res.getNumConsecutiveCollisions());
  }

  @Test
  public void checkThrowsException() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();
    Entity collider = new StaticEntity(0, 0, 100, 50, new Info());
    Entity collided = new MainCharacter(new DefaultCollisionChart(), 500, 1000, 100, 50, new EntityInfo("MARIO"));

    CollisionPhysicsData res = physicsCalculator.calculatePhysicsData(collider, collided);
    assertThrows(RuntimeException.class, () -> res.getCollisionDirection());
  }


}
