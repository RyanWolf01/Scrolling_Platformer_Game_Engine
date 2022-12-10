package ooga.model.actions.moveractions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
import org.junit.jupiter.api.Test;

public class StopYMovementTest {

  @Test
  void testStopYMovementPos1() {
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null);

    StopYMovement stop = new StopYMovement();
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * stop twice
   */
  @Test
  void testStopYMovementPos2() {
    MainCharacter mario = new MainCharacter(null,0, 0, 2, 2, null);

    StopYMovement stop = new StopYMovement();
    stop.execute(mario);
    stop.execute(mario);

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

  /**
   * don't execute
   */
  @Test
  void testStopYMovementNeg() {
    MainCharacter mario = new MainCharacter(null, 0, 0, 2, 2, null);

    StopYMovement stop = new StopYMovement();

    assertEquals(0, mario.getXCoordinate());
    assertEquals(0, mario.getYCoordinate());
  }

}
