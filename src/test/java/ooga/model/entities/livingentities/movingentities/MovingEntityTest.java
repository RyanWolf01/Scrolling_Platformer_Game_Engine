package ooga.model.entities.livingentities.movingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.entitymodels.MainCharacter;
import org.junit.jupiter.api.Test;

public class MovingEntityTest {

  @Test
  void resetVelocitiesTestPositive1() {
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"));
    mario.changeVelocities(1,1);
    mario.resetVelocities(true, true);
    assertEquals(0, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }

  @Test
  void resetVelocitiesTestPositive2() {
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"));
    mario.changeVelocities(1,1);
    mario.resetVelocities(false, true);
    assertEquals(1, mario.getXVelocity());
    assertEquals(0, mario.getYVelocity());
  }

  @Test
  void resetVelocitiesTestNegative() {
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"));
    mario.changeVelocities(1,1);
    mario.resetVelocities(false, false);
    assertEquals(1, mario.getXVelocity());
    assertEquals(1, mario.getYVelocity());
  }

}
