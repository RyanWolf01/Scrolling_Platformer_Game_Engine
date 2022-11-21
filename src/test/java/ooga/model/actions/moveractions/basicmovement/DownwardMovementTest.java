package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.characters.maincharacters.Mario;
import org.junit.jupiter.api.Test;

public class DownwardMovementTest {

  @Test
  void testMovementPos1() {
    Mario mario = new Mario(0, 15, 2, 2, null);

    DownwardMovement move = new DownwardMovement();
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(10, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testMovementPos2() {
    Mario mario = new Mario(0, 15, 2, 2, null);

    DownwardMovement move = new DownwardMovement();
    move.execute(mario);
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testMovementNeg() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    DownwardMovement move = new DownwardMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
