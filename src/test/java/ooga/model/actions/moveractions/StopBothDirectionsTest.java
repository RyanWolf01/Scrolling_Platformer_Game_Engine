package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.characters.maincharacters.Mario;
import org.junit.jupiter.api.Test;

public class StopBothDirectionsTest {

  @Test
  void testStopPos1() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    StopBothDirections stop = new StopBothDirections();
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testStopPos2() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    StopBothDirections stop = new StopBothDirections();
    stop.execute(mario);
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testStopNeg() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    StopBothDirections stop = new StopBothDirections();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}

