package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class RightMovementTest {

  private final double rightVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("right_velocity"));

  @Test
  void testMovementPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    RightMovement move = new RightMovement();
    move.execute(mario);

    assertEquals(rightVelocity, mario.getXVelocity());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testMovementPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    RightMovement move = new RightMovement();
    move.execute(mario);
    move.execute(mario);

    assertEquals(rightVelocity, mario.getXVelocity());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testMovementNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    RightMovement move = new RightMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
