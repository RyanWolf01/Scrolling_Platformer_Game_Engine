package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.StopXMovement;
import ooga.model.entities.livingentities.movingentities.maincharacters.marios.Mario;
import org.junit.jupiter.api.Test;

public class UpwardMovementTest {

  private final double upVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));

  @Test
  void testUpwardMovementPos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    UpwardMovement move = new UpwardMovement();
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(upVelocity, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testUpwardMovementPos2() {
    Mario mario = new Mario(null,0, 0, 2, 2, null);

    UpwardMovement move = new UpwardMovement();
    move.execute(mario);
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(upVelocity * 2, mario.getYVelocity());
  }

  /**
   * don't execute
   */
  @Test
  void testUpwardMovementNeg() {
    Mario mario = new Mario(null,0, 0, 2, 2, null);

    StopXMovement move = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
