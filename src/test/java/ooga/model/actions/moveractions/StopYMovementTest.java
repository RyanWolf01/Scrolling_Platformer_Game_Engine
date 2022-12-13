package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import org.junit.jupiter.api.Test;

public class StopYMovementTest {

  @Test
  void testStopYMovementPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
    StopYMovement stop = new StopYMovement();
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testStopYMovementPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
    StopYMovement stop = new StopYMovement();
    stop.execute(mario);
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testStopYMovementNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
    StopYMovement stop = new StopYMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
