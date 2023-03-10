package ooga.model.entities.collidable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.controller.JSONInformationDecoder;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.collisions.collisionhandling.CollisionChartGetter;
import ooga.model.collisions.collisionhandling.DefaultCollisionChartGetter;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.collisions.physics.PhysicsCalculator;

import ooga.model.entities.entitymodels.BasicStaticCharacter;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.maincharacter.MainCharacter;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

public class CollidableEntityTest {

  @Test
  void test_onCollision() {
    JSONInformationDecoder decoder = new JSONInformationDecoder("sprint_1_test/level.json", "sprint_1_test/collisions.json",
        "sprint_1_test/controls.json");
    CollisionChartGetter ccg = new DefaultCollisionChartGetter();
    CollisionChart marioChart = ccg.getCollisionChart(decoder, "mario");

    BasicMainCharacter mario = new BasicMainCharacter(marioChart,11, 0, 50, 20, new EntityInfo("MARIO"), new MovementQueue());

    BasicStaticCharacter goomba = new BasicStaticCharacter(null, 30, 0, 50, 20, new EntityInfo("GOOMBA"));

    PhysicsCalculator phyCalc = new PhysicsCalculator();
    CollisionPhysicsData cpi = phyCalc.calculatePhysicsData(mario, goomba);

    int numLives = mario.getLives();
    mario.changeVelocities(18, 7);

    mario.onCollision(goomba, cpi);

    assertEquals(numLives - 1, mario.getLives());
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());

  }

  @Test
  void test_onCollision2() {
    JSONInformationDecoder decoder = new JSONInformationDecoder("sprint_1_test/level.json", "sprint_1_test/collisions.json",
        "sprint_1_test/controls.json");
    CollisionChartGetter ccg = new DefaultCollisionChartGetter();
    CollisionChart marioChart = ccg.getCollisionChart(decoder, "mario");


    BasicMainCharacter mario = new BasicMainCharacter(marioChart, 0, 45, 50, 20, new EntityInfo("MARIO"), new MovementQueue());
    BasicStaticCharacter goomba = new BasicStaticCharacter(null, 0, 0, 51, 22, new EntityInfo("GOOMBA"));

    PhysicsCalculator phyCalc = new PhysicsCalculator();
    CollisionPhysicsData cpi = phyCalc.calculatePhysicsData(mario, goomba);

    int goombaLives = goomba.getLives();
    double yVel = mario.getYVelocity();

    mario.onCollision(goomba, cpi);

    assertEquals(goombaLives - 1, goomba.getLives());
    assertEquals(yVel + 5, mario.getYVelocity());
  }
}
