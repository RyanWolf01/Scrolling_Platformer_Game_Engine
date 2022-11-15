package ooga.model.actions.moveractions;


import ooga.model.entities.EntityInfo;
import ooga.model.entities.characters.Mario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BounceTest {

  @Test
  void testBouncePos1() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    Bounce bounce = new Bounce();
    bounce.execute(mario);

    assertEquals(5, mario.getYCoordinate());
  }

  /**
   * bounce twice
   */
  @Test
  void testBouncePos2() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    Bounce bounce = new Bounce();
    bounce.execute(mario);

    Bounce bounce2 = new Bounce();
    bounce2.execute(mario);

    assertEquals(10, mario.getYCoordinate());
  }

  @Test
  void testBounceNeg() {
    Mario mario = new Mario(0, 0, 2, 2, null);

    Bounce bounce = new Bounce();

    assertEquals(0, mario.getYCoordinate());
  }

}
