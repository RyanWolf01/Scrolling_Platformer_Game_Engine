package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.entitymodels.MainCharacter;
import org.junit.jupiter.api.Test;

public class LeftMovementTest {

  private final double leftVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("left_velocity"));

  @Test
  void testMovementPos1() {
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);

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
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);

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
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, null);

    LeftMovement move = new LeftMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
