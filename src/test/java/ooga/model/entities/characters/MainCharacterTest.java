package ooga.model.entities.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.entities.EntityInfo;
import org.junit.jupiter.api.Test;

public class MainCharacterTest {

  @Test
  void testKillPos1() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    mario.kill();
    assertEquals(1, mario.getLives());
  }

  @Test
  void testKillPos2() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    mario.kill();
    mario.kill();
    assertEquals(1, mario.getLives());
  }

  @Test
  void testSetLivesPos1() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.setLives(100);
    assertEquals(100, mario.getLives());
  }

  @Test
  void testSetLivesPos2() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.setLives(-100);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testSetLivesNeg() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.setLives(0);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testChangeVelocitiesPos1() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(1,1);
    assertEquals(1, mario.getXVelocity());
    assertEquals(1, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesPos2() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(100,0);
    assertEquals(100, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }

  @Test
  void testChangeVelocitiesNeg() {
    MainCharacter mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,0);
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }


}
