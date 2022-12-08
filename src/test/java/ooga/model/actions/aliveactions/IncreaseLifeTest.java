package ooga.model.actions.aliveactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.livingentities.movingentities.maincharacters.marios.Mario;
import org.junit.jupiter.api.Test;

public class IncreaseLifeTest {

  @Test
  void testIncreaseLifePos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);

    assertEquals(2, mario.getLives());
  }

  /**
   * execute twice
   */
  @Test
  void testIncreaseLifePos2() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    assertEquals(3, mario.getLives());
  }

  /**
   * don't execute
   */
  @Test
  void testIncreaseLifeNeg() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    IncreaseLife increaseLife = new IncreaseLife();

    assertEquals(1, mario.getLives());
  }

}

