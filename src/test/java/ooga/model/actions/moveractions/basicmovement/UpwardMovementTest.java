package ooga.model.actions.moveractions.basicmovement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.StopXMovement;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.entitymodels.StaticEntity;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class UpwardMovementTest {

  private final double upVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));

  @Test
  void testUpwardMovementPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);
    StaticEntity staticEntity = new StaticEntity(0, 0, 5, 100, new EntityInfo("PLATFORM"));

    UpwardMovement move = new UpwardMovement();
    move.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertNotEquals(upVelocity, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testUpwardMovementPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

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
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    StopXMovement move = new StopXMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
