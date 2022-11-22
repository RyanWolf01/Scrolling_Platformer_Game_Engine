package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.characters.maincharacters.Mario;
import org.junit.jupiter.api.Test;

public class StopYMovementTest {

  @Test
  void testStopYMovementPos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

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
    Mario mario = new Mario(null,0, 0, 2, 2, null);

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
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    StopYMovement stop = new StopYMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
