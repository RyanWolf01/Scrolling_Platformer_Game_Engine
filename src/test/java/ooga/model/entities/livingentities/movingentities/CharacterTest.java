package ooga.model.entities.livingentities.movingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.movingentities.maincharacters.Mario;
import ooga.model.entities.livingentities.movingentities.MovingCharacter;
import org.junit.jupiter.api.Test;

public class CharacterTest {

  @Test
  void testKillPos1() {
    MovingCharacter mario = new Mario(null, 0, 0, 2, 2, new EntityInfo("MARIO"));

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    mario.kill();
    assertEquals(2, mario.getLives());
  }

  @Test
  void testKillPos2() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

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
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(100);
    assertEquals(101, mario.getLives());
  }

  @Test
  void testSetLivesPos2() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(-100);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testSetLivesNeg() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(0);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testChangeVelocitiesPos1() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(1,1);
    assertEquals(1, mario.getXVelocity());
    assertEquals(1, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesPos2() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(100,0);
    assertEquals(100, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesNeg() {
    MovingCharacter mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,0);
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }


}
