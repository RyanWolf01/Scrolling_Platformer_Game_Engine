package ooga.model.entities.livingentities.movingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import org.junit.jupiter.api.Test;

public class CharacterTest {

  @Test
  void testKillPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    mario.kill();
    assertEquals(2, mario.getLives());
  }

  @Test
  void testKillPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    mario.kill();
    mario.kill();
    assertEquals(2, mario.getLives());
  }

  @Test
  void testSetLivesPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeLives(100);
    assertEquals(101, mario.getLives());
  }

  @Test
  void testSetLivesPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeLives(-100);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testSetLivesNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeLives(0);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testChangeVelocitiesPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeVelocities(1,1);
    assertEquals(1, mario.getXVelocity());
    assertEquals(1, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeVelocities(100,0);
    assertEquals(100, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeVelocities(0,0);
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }


}
