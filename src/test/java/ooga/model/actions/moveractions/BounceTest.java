package ooga.model.actions.moveractions;


import java.util.ResourceBundle;
import ooga.model.entities.livingentities.movingentities.maincharacters.Mario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BounceTest {

  private final double bounceVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

  @Test
  void testBouncePos1() {
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

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
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

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
    Mario mario = new Mario(null, 0, 0, 2, 2, null);

    Bounce bounce = new Bounce();

    assertEquals(0, mario.getYCoordinate());
    assertEquals(0, mario.getXCoordinate());
  }

}
