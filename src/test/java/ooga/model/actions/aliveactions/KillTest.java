package ooga.model.actions.aliveactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.livingentities.movingentities.maincharacters.marios.Mario;
import org.junit.jupiter.api.Test;

public class KillTest {

  @Test
  void testKillPos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    Kill kill = new Kill();
    kill.execute(mario);

    assertEquals(2, mario.getLives());
  }

  /**
   * execute twice
   */
  @Test
  void testKillPos2() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    Kill kill = new Kill();
    kill.execute(mario);
    kill.execute(mario);

    assertEquals(1, mario.getLives());
  }

  /**
   * don't execute
   */
  @Test
  void testKillNeg() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    Kill kill = new Kill();

    assertEquals(0, mario.getLives());
  }

}


