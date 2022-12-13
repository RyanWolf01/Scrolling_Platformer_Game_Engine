package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import org.junit.jupiter.api.Test;

public class StopBothDirectionsTest {

  @Test
  void testStopPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
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
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
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
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);
    StopBothDirections stop = new StopBothDirections();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}

