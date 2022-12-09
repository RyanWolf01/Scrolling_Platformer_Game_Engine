package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.livingentities.movingentities.maincharacters.marios.Mario;
import org.junit.jupiter.api.Test;

public class RightMovementTest {

  private final double rightVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("right_velocity"));

  @Test
  void testMovementPos1() {
    Mario mario = new Mario(null,0, 0, 2, 2, null);

    RightMovement move = new RightMovement();
    move.execute(mario);

    assertEquals(rightVelocity, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testMovementPos2() {
    Mario mario = new Mario(null,0, 0, 2, 2, null);

    RightMovement move = new RightMovement();
    move.execute(mario);
    move.execute(mario);

    assertEquals(rightVelocity * 2, mario.getXVelocity());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testMovementNeg() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    RightMovement move = new RightMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
