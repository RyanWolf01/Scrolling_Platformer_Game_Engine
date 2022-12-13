package ooga.model.entities.deadmovingentities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ooga.controller.exceptions.BadDatabaseAccessException;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.Mover;
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

  @Test
  void testSetLeftVelocityNegative(){
    MoverData data = new MoverData(new EntityInfo("hi"));

    assertThrows(MovementDataException.class, () -> {
      data.setLeftActionVelocity(1);
    });
  }

  @Test
  void testSetLeftVelocityPositive(){
    MoverData data = new MoverData(new EntityInfo("hi"));
    data.setLeftActionVelocity(-1);
    assertEquals(-1, data.getLeftActionVelocity());
  }

  @Test
  void testSetRightVelocityNegative(){
    MoverData data = new MoverData(new EntityInfo("hi"));

    assertThrows(MovementDataException.class, () -> {
      data.setRightActionVelocity(-1);
    });
  }

  @Test
  void testSetRightVelocityPositive(){
    MoverData data = new MoverData(new EntityInfo("hi"));
    data.setRightActionVelocity(100);
    assertEquals(100, data.getRightActionVelocity());
  }

  @Test
  void testSetDownwardVelocityNegative(){
    MoverData data = new MoverData(new EntityInfo("hi"));

    assertThrows(MovementDataException.class, () -> {
      data.setDownwardActionVelocity(-1);
    });
  }

  @Test
  void testSetDownwardVelocityPositive(){
    MoverData data = new MoverData(new EntityInfo("hi"));
    data.setDownwardActionVelocity(100);
    assertEquals(100, data.getDownwardActionVelocity());
  }

  @Test
  void testSetUpwardVelocityNegative(){
    MoverData data = new MoverData(new EntityInfo("hi"));

    assertThrows(MovementDataException.class, () -> {
      data.setUpwardActionVelocity(1);
    });
  }

  @Test
  void testSetUpwardVelocityPositive(){
    MoverData data = new MoverData(new EntityInfo("hi"));
    data.setUpwardActionVelocity(-100);
    assertEquals(-100, data.getUpwardActionVelocity());
  }

  @Test
  void testSetGravityVelocityNegative(){
    MoverData data = new MoverData(new EntityInfo("hi"));

    assertThrows(MovementDataException.class, () -> {
      data.setGravityVelocity(-1);
    });
  }

  @Test
  void testSetGravityVelocityPositive(){
    MoverData data = new MoverData(new EntityInfo("hi"));
    data.setGravityVelocity(100);
    assertEquals(100, data.getGravityVelocity());
  }

}
