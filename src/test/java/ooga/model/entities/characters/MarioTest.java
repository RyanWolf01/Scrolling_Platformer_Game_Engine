package ooga.model.entities.characters;

import ooga.model.entities.EntityInfo;
import ooga.model.entities.data.InitialAttributes;
import org.junit.jupiter.api.Test;
import static ooga.model.entities.data.EntityType.MAIN_CHARACTER;
import static org.junit.jupiter.api.Assertions.*;

public class MarioTest {

  @Test
  void testIncrementXVelocityPositive1() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(5,0);
    mario.move();
    assertEquals(5, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(-100,0);
    mario.move();
    assertEquals(-100, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,0);
    mario.move();
    assertEquals(0, mario.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,5);
    mario.move();
    assertEquals(5, mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,-100);
    mario.move();
    assertEquals(-100, mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,0);
    mario.move();
    assertEquals(0, mario.getYCoordinate());
  }

  @Test
  void testIncrementLivesPositive1() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(1);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(100);
    assertEquals(100, mario.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(0);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(1);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(101);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesNegative() {
    Mario mario = new Mario(0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(0);
    assertEquals(0, mario.getLives());
  }


}
