package ooga.model.entities.deadmovingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.MoverData;
import org.junit.jupiter.api.Test;

public class MoverDataTest {

  @Test
  void testConstructor() {
    EntityInfo entityInfo = new EntityInfo("test");
    entityInfo.set("rightActionVelocity", "25");
    entityInfo.set("leftActionVelocity", "-25");
    entityInfo.set("upwardActionVelocity", "250");
    entityInfo.set("downwardActionVelocity", "-250");
    entityInfo.set("gravityVelocity", "0.26");
    MoverData moverData = new MoverData(entityInfo);

    assertEquals(25, moverData.getRightActionVelocity());
    assertEquals(-25, moverData.getLeftActionVelocity());
    assertEquals(250, moverData.getUpwardActionVelocity());
    assertEquals(-250, moverData.getDownwardActionVelocity());
    assertEquals(0.26, moverData.getGravityVelocity());
  }

}
