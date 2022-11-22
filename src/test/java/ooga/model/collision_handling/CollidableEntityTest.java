package ooga.model.collision_handling;

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

    mario.onCollision(goomba, cpi);


  }
}
