package ooga.model.collisions.physics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.containers.BackendContainer;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GravityEnforcerTest {

  private double gravityVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

  private JSONInformationDecoder decoder;
  private BackendContainer container;

  @BeforeEach
  public void setup(){
    decoder = new JSONInformationDecoder("data/games/sprint_1_test/level.json",
        "data/games/sprint_1_test/collisions.json",
        "data/games/sprint_1_test/controls.json");
    container = new BackendContainer(decoder);
    EntityInfo info = new EntityInfo("mario");
    info.set("texture", "idk");
    container.addNewEntity(0, 0, 0, 0, "mario", info);
    container.mainCharacter().changeVelocities(0, 10);
  }

  @Test
  void testEnforceGravityPos1(){
    GravityEnforcer gravityEnforcer = new GravityEnforcer(container);
    gravityEnforcer.applyGravityToAllMovers();

    assertEquals(10 + gravityVelocity, container.mainCharacter().getYVelocity());
  }

  @Test
  void testEnforceGravityPos2(){
    GravityEnforcer gravityEnforcer = new GravityEnforcer(container);
    gravityEnforcer.applyGravityToAllMovers();
    gravityEnforcer.applyGravityToAllMovers();

    assertEquals(10 + gravityVelocity * 2, container.mainCharacter().getYVelocity());
  }

  @Test
  void testEnforceGravityNegative(){
    GravityEnforcer gravityEnforcer = new GravityEnforcer(container);
    assertEquals(10 , container.mainCharacter().getYVelocity());
  }




}
