package ooga.model.entities.livingentities.movingentities;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.actions.moveractions.basicmovement.UpwardMovement;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.entitymodels.BasicMovingEntity;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticMovingCharacterTest {

  private final double bounceVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("bounce_velocity"));

  private final double rightVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("right_velocity"));
  private final double upwardVelocity = Double.parseDouble(
      ResourceBundle.getBundle("properties/movement").getString("upward_velocity"));
  @Test
  void testIncrementXVelocityPositive1() {

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null, 0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(5,0);
    movingCharacter.move();
    assertEquals(5, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null, 0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(-100,0);
    movingCharacter.move();
    assertEquals(-95, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,0);
    movingCharacter.move();
    assertEquals(0, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,5);
    movingCharacter.move();
    assertEquals(5 , movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,-100);
    movingCharacter.move();
    assertEquals(-100 , movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,0);
    movingCharacter.move();
    assertEquals(0, movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementLivesPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(1);
    assertEquals(2, movingCharacter.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(100);
    assertEquals(101, movingCharacter.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(0);
    assertEquals(1, movingCharacter.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(-1);
    assertEquals(0, movingCharacter.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(100);
    movingCharacter.changeLives(-1);
    assertEquals(100, movingCharacter.getLives());
  }

  @Test
  void testAutomaticMovePos1(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);
    movingCharacter.move();
    assertEquals(rightVelocity, movingCharacter.getXCoordinate());
  }

  @Test
  void testAutomaticMovePos2(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new UpwardMovement());

    MovingCharacter movingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);
    movingCharacter.move();
    movingCharacter.move();
    movingCharacter.move();
    assertEquals(0, movingCharacter.getXCoordinate());
    assertEquals(upwardVelocity * 2 , movingCharacter.getYCoordinate());
  }

  @Test
  void testPerformActionsPos1(){
    MovementQueue movementQueue = new MovementQueue();
    MovingCharacter character = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.aliveactions.IncreaseLife", "AliveAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
//    character.performActions(container);

    assertEquals(1, character.getLives());
  }

  @Test
  void testPerformActionsPos2(){
    MovementQueue movementQueue = new MovementQueue();
    MovingCharacter character = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.moveractions.Bounce", "MoverAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);
//    character.performActions(container);

    assertEquals(0, character.getYCoordinate());
  }

  @Test
  void handleInvalidCoordinatesTestOffBottomPositive1(){
    MovingCharacter automaticMovingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    automaticMovingCharacter.changeLives(1);
    automaticMovingCharacter.changeVelocities(0, 10000000);
    automaticMovingCharacter.move();

    assertEquals(1, automaticMovingCharacter.getLives());
  }

  @Test
  void handleInvalidCoordinatesTestOffBottomPositive2(){
    MovingCharacter automaticMovingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    automaticMovingCharacter.changeLives(3);
    automaticMovingCharacter.changeVelocities(0, 100000);
    automaticMovingCharacter.move();

    assertEquals(3, automaticMovingCharacter.getLives());
  }

  @Test
  void handleInvalidCoordinatesTestOffBottomNegative(){
    MovingCharacter automaticMovingCharacter = new MovingCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    int screenSize = Integer.parseInt(
        ResourceBundle.getBundle("properties/view").getString("screen_size"));
    automaticMovingCharacter.changeLives(3);
    automaticMovingCharacter.changeVelocities(0, screenSize-1);
    automaticMovingCharacter.move();

    assertEquals(4, automaticMovingCharacter.getLives());
  }


}
