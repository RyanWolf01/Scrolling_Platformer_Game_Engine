package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
import org.junit.jupiter.api.Test;

public class RightMovementTest {

  private final double rightVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("right_velocity"));

  @Test
  void testMovementPos1() {
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, null);

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
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, null);

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
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null);

    RightMovement move = new RightMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
