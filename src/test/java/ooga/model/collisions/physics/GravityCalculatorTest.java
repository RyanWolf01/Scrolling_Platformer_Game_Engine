package ooga.model.collisions.physics;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.entities.Entity;
import ooga.model.entities.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.movingentities.maincharacters.Mario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GravityCalculatorTest {

  private Mario mario;

  @BeforeEach
  public void setup(){
    EntityInfo info = new EntityInfo("mario");
    mario = new Mario(null, 10, 10, 2, 2, info);
  }

  @Test
  void testEnforceGravityPos1(){
    GravityCalculator gravityCalculator = new GravityCalculator();
    assertTrue(gravityCalculator.checkInAir(mario));
  }

  @Test
  void testEnforceGravityPos2(){
    GravityCalculator gravityCalculator = new GravityCalculator();
    assertTrue(gravityCalculator.checkInAir(mario));
  }

  @Test
  void testEnforceGravityNegative(){
    GravityCalculator gravityCalculator = new GravityCalculator();

    Entity platform = new StaticEntity(0,0,100,100, new EntityInfo("platform"));

    CollisionPhysicsInfo physicsInfo = new CollisionPhysicsInfo(true, 0, CollisionDirection.LEFT);
    mario.getMyCurrentCollisions().set(platform, physicsInfo);
    assertFalse(gravityCalculator.checkInAir(mario));
  }

}
