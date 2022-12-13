package ooga.model.actions.aliveactions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class KillTest {

  @Test
  void testKillPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    Kill kill = new Kill();
    kill.execute(mario);

    assertEquals(3, mario.getLives());
  }

  /**
   * execute twice
   */
  @Test
  void testKillPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    IncreaseLife increaseLife = new IncreaseLife();
    increaseLife.execute(mario);
    increaseLife.execute(mario);
    increaseLife.execute(mario);

    Kill kill = new Kill();
    kill.execute(mario);
    kill.execute(mario);

    assertEquals(2, mario.getLives());
  }

  /**
   * don't execute
   */
  @Test
  void testKillNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    Kill kill = new Kill();

    assertEquals(1, mario.getLives());
  }

}


