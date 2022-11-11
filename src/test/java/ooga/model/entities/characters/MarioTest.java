package ooga.model.entities.characters;

import ooga.model.entities.data.InitialAttributes;
import org.junit.jupiter.api.Test;
import static ooga.model.entities.data.EntityType.MAIN_CHARACTER;
import static org.junit.jupiter.api.Assertions.*;

public class MarioTest {

  @Test
  void testIncrementXVelocityPositive1() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementXVelocity(5);
    mario.move();
    assertEquals(5, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementXVelocity(-100);
    mario.move();
    assertEquals(-100, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementXVelocity(0);
    mario.move();
    assertEquals(0, mario.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementYVelocity(5);
    mario.move();
    assertEquals(5, mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementYVelocity(-100);
    mario.move();
    assertEquals(-100, mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.incrementYVelocity(0);
    mario.move();
    assertEquals(0, mario.getYCoordinate());
  }

  @Test
  void testIncrementLivesPositive1() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.increaseLives(1);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.increaseLives(100);
    assertEquals(100, mario.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.increaseLives(0);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 1);

    mario.decreaseLives(1);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 100);

    mario.decreaseLives(101);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesNegative() {
    Mario mario = new Mario(new InitialAttributes(0, 0, 2,2, MAIN_CHARACTER), 0);

    mario.decreaseLives(0);
    assertEquals(0, mario.getLives());
  }


}
