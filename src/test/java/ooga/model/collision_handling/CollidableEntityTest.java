package ooga.model.collision_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.characters.BasicStaticCharacter;
import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.data.EntityInfo;
import org.junit.jupiter.api.Test;

public class CollidableEntityTest {

  @Test
  void test_onCollision() {
    Mario mario = new Mario(0, 0, 50, 20, new EntityInfo("MARIO"));
    BasicStaticCharacter goomba = new BasicStaticCharacter(30, 0, 50, 20, new EntityInfo("GOOMBA"));
    mario.setXCoordinate(11);

    PhysicsCalculator phyCalc = new PhysicsCalculator();
    CollisionPhysicsInfo cpi = phyCalc.calculatePhysics(mario, goomba);

    int numLives = mario.getLives();
    mario.changeVelocities(18, 7);

    mario.onCollision(goomba, cpi);

//    assertEquals(numLives - 1, mario.getLives());
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());

  }
}
