package ooga.model.actions.aliveactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class IncreaseLifeTest {

  @Test
  void testIncreaseLifePos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);

    assertEquals(2, mario.getLives());
  }

  /**
   * execute twice
   */
  @Test
  void testIncreaseLifePos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

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
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();

    assertEquals(1, mario.getLives());
  }

}

