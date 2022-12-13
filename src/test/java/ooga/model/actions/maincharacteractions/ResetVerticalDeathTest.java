package ooga.model.actions.maincharacteractions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.collisions.collisionhandling.DefaultCollisionChart;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

public class ResetVerticalDeathTest {

  @Test
  void testExecute_hasPowerup() {
    BasicMainCharacter mainCharacter = new BasicMainCharacter(new DefaultCollisionChart(), 0, 0, 10, 10, new EntityInfo("Mario"), new MovementQueue());
    mainCharacter.setHasPowerUp(true);
    double originalY = mainCharacter.getYCoordinate();

    new ResetVerticalDeath().execute(mainCharacter);

    assertFalse(mainCharacter.hasPowerUp());
    assertTrue(mainCharacter.getYCoordinate() < originalY);

  }

  @Test
  void testExecute_noPowerup() {
    BasicMainCharacter mainCharacter = new BasicMainCharacter(new DefaultCollisionChart(), 0, 0, 10, 10, new EntityInfo("Mario"), new MovementQueue());
    mainCharacter.setHasPowerUp(false);
    int numLives = mainCharacter.getLives();

    new ResetVerticalDeath().execute(mainCharacter);

    assertNotEquals(numLives, mainCharacter.getLives());

  }

}
