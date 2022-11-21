package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.actions.moveractions.StopXMovement;
import ooga.model.entities.characters.maincharacters.Mario;
import org.junit.jupiter.api.Test;

public class UpwardMovementTest {

  @Test
  void testUpwardMovementPos1() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    UpwardMovement move = new UpwardMovement();
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(5, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testUpwardMovementPos2() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    UpwardMovement move = new UpwardMovement();
    move.execute(mario);
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(15, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testUpwardMovementNeg() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    StopXMovement move = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
