package ooga.model.actions.moveractions;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import org.junit.jupiter.api.Test;

public class BounceTest {

  private final double bounceVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

  @Test
  void testBouncePos1() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);

    Bounce bounce = new Bounce();
    bounce.execute(mario);

    assertEquals(bounceVelocity, mario.getYVelocity());
    assertEquals(0, mario.getXVelocity());
  }

  /**
   * bounce twice
   */
  @Test
  void testBouncePos2() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);

    Bounce bounce = new Bounce();
    bounce.execute(mario);

    Bounce bounce2 = new Bounce();
    bounce2.execute(mario);

    assertEquals(bounceVelocity * 2, mario.getYVelocity());
    assertEquals(0, mario.getXVelocity());
  }

  /**
   * don't execute
   */
  @Test
  void testBounceNeg() {
    BasicMainCharacter mario = new BasicMainCharacter(null, 0, 0, 2, 2, null, null);

    Bounce bounce = new Bounce();

    assertEquals(0, mario.getYCoordinate());
    assertEquals(0, mario.getXCoordinate());
  }

}
