package ooga.model.actions.maincharacteractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

public class IncrementScoreStandardTest {

  @Test
  void testExecute() {
    BasicMainCharacter mainCharacter = new BasicMainCharacter(new DefaultCollisionChart(), 0, 0, 10, 10, new EntityInfo("Mario"), new MovementQueue());
    int score1 = mainCharacter.getScore();
    new IncrementScoreStandard().execute(mainCharacter);

    assertEquals(score1 + 1, mainCharacter.getScore());
  }
}
