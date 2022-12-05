package ooga.model.entities.livingentities.movingentities.maincharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class MainCharacterTest {

  @Test
  void testIncrementXVelocityPositive1() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(5,0);
    mario.move();
    assertEquals(5, mario.getXCoordinate());
  }

}
