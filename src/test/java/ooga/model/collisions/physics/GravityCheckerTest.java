package ooga.model.collisions.physics;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GravityCheckerTest {

  private BasicMainCharacter mario;

  @BeforeEach
  public void setup(){
    EntityInfo info = new EntityInfo("mario");
    mario = new BasicMainCharacter(null, 10, 10, 2, 2, info, null);
  }

  @Test
  void testEnforceGravityPos1(){
    GravityChecker gravityChecker = new GravityChecker();
    assertTrue(gravityChecker.checkInAir(mario));
  }

  @Test
  void testEnforceGravityPos2(){
    GravityChecker gravityChecker = new GravityChecker();

    Entity platform = new StaticEntity(0,0,100,100, new EntityInfo("platform"));
    CollisionPhysicsData physicsInfo = new CollisionPhysicsData(true, 0, CollisionDirection.TOP);
    mario.getMyCurrentCollisions().set(platform, physicsInfo);

    assertTrue(gravityChecker.checkInAir(mario));
  }

  @Test
  void testEnforceGravityNegative(){
    GravityChecker gravityChecker = new GravityChecker();

    Entity platform = new StaticEntity(0,0,100,100, new EntityInfo("platform"));
    CollisionPhysicsData physicsInfo = new CollisionPhysicsData(true, 0, CollisionDirection.BOTTOM);
    mario.getMyCurrentCollisions().set(platform, physicsInfo);

    assertFalse(gravityChecker.checkInAir(mario));
  }

}
