package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.livingentities.movingentities.maincharacters.Mario;
import org.junit.jupiter.api.Test;

public class StopXMovementTest {

  @Test
  void testStopXMovementPos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    StopXMovement stop = new StopXMovement();
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testStopXMovementPos2() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    StopXMovement stop = new StopXMovement();
    stop.execute(mario);
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
}

  /**
   * don't execute
   */
  @Test
  void testStopXMovementNeg() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    StopXMovement stop = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
