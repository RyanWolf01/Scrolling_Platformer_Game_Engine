package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.StopXMovement;
import ooga.model.entities.deadmovingentities.MovementQueue;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
import org.junit.jupiter.api.Test;

public class UpwardMovementTest {

  private final double upVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));

  @Test
  void testUpwardMovementPos1() {
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null, new MovementQueue());

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
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, null, new MovementQueue());

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
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, null, new MovementQueue());

    StopXMovement move = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
