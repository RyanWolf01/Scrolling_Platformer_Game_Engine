package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.MainCharacter;
import org.junit.jupiter.api.Test;

public class StopXMovementTest {

  @Test
  void testStopXMovementPos1() {
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);
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
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);
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
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);
    StopXMovement stop = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
