package ooga.model.entities.livingentities.movingentities.maincharacters;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.containers.EntityContainer;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarioTest {

  @Test
  void testIncrementXVelocityPositive1() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(5,0);
    mario.move();
    assertEquals(5, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(-100,0);
    mario.move();
    assertEquals(0, mario.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(0,0);
    mario.move();
    assertEquals(0, mario.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    mario.changeVelocities(0,5);
    mario.move();
    assertEquals(5 + gravityVelocity, mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    mario.changeVelocities(0,10000);
    mario.move();
    assertEquals(10000 +gravityVelocity , mario.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    mario.changeVelocities(0,0);
    mario.move();
    assertEquals(gravityVelocity, mario.getYCoordinate());
  }

  @Test
  void testIncrementLivesPositive1() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(1);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(100);
    assertEquals(100, mario.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(0);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(1);
    assertEquals(1, mario.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(101);
    assertEquals(101, mario.getLives());
  }

  @Test
  void testDecrementLivesNegative() {
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(0);
    assertEquals(0, mario.getLives());
  }

  @Test
  void testPerformActionsPos1(){
    Mario character = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.IncreaseLife", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
    character.performActions(container);

    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsPos2(){
    Mario character = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    double bounceVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.moveractions.Bounce", "MoverAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
    character.performActions(container);

    assertEquals(bounceVelocity + gravityVelocity, character.getYCoordinate());
  }

  @Test
  void handleInvalidCoordinatesTestOffLeft(){
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(-1, 0);
    mario.move();

    assertEquals(0, mario.getXCoordinate());
  }
  @Test
  void handleInvalidCoordinatesTestOffRight(){
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeVelocities(10000000, 0);
    mario.move();

    assertEquals(99999.0, mario.getXCoordinate());
  }

  @Test
  void handleInvalidCoordinatesTestOffBottom(){
    Mario mario = new Mario(null,0, 0, 2, 2, new EntityInfo("MARIO"));

    mario.changeLives(1);
    mario.changeVelocities(0, 10000000);
    mario.move();

    assertEquals(0, mario.getLives());
  }

}
