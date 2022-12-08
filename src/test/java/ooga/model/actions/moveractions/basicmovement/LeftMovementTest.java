package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.livingentities.movingentities.maincharacters.marios.Mario;
import org.junit.jupiter.api.Test;

public class LeftMovementTest {

  private final double leftVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("left_velocity"));

  @Test
  void testMovementPos1() {
    Mario mario = new Mario(null, 100, 0, 2, 2, null);

    LeftMovement move = new LeftMovement();
    move.execute(mario);

    assertEquals(100 + leftVelocity, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testMovementPos2() {
    Mario mario = new Mario(null, 10, 0, 2, 2, null);

    LeftMovement move = new LeftMovement();
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
    Mario mario = new Mario(null,0, 0, 2, 2, null);

    LeftMovement move = new LeftMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
