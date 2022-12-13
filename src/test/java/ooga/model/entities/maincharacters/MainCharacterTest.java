package ooga.model.entities.maincharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.actions.aliveactions.Kill;
import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class MainCharacterTest {

  private final double gravityVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

  private final double bounceVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

  private final double rightVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("right_velocity"));
  private final double upwardVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));

  @Test
  void testAcceptMoverActionPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.acceptMoveAction(new RightMovement());
    assertEquals(rightVelocity, mario.getXVelocity());
  }
  @Test
  void testAcceptMoverActionPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.acceptMoveAction(new RightMovement());
    mario.acceptMoveAction(new RightMovement());
    mario.acceptMoveAction(new LeftMovement());
    assertEquals(0, mario.getXVelocity());
  }

  @Test
  void testAcceptAliveActionPos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.acceptAliveAction(new IncreaseLife());
    assertEquals(2, mario.getLives());
  }

  @Test
  void testAcceptAliveActionPos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.acceptAliveAction(new IncreaseLife());
    mario.acceptAliveAction(new IncreaseLife());
    mario.acceptAliveAction(new Kill());
    assertEquals(2, mario.getLives());
  }



}
