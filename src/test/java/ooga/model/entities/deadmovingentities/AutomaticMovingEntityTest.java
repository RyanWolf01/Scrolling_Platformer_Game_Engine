package ooga.model.entities.deadmovingentities;

import java.util.ArrayList;
import java.util.List;
import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.actions.moveractions.basicmovement.UpwardMovement;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.entitymodels.BasicMovingEntity;
import ooga.model.entities.entitymodels.MovingEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticMovingEntityTest {

  @Test
  void testIncrementXVelocityPositive1() {

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(5,0);
    movingEntity.move();
    assertEquals(5, movingEntity.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(-100,0);
    movingEntity.move();
    assertEquals(-95, movingEntity.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(0,0);
    movingEntity.move();
    assertEquals(5, movingEntity.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(0,5);
    movingEntity.move();
    assertEquals(5, movingEntity.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(0,-100);
    movingEntity.move();
    assertEquals(-100, movingEntity.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);

    movingEntity.changeVelocities(0,0);
    movingEntity.move();
    assertEquals(0, movingEntity.getYCoordinate());
  }

  @Test
  void testAutomaticMovePos1(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new RightMovement());

    BasicMovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);
    movingEntity.move();
    assertEquals(5, movingEntity.getXCoordinate());
  }

  @Test
  void testAutomaticMovePos2(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new UpwardMovement());

    BasicMovingEntity movingEntity = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("test"), movementQueue);
    movingEntity.move();
    movingEntity.move();
    movingEntity.move();
    assertEquals(0, movingEntity.getXCoordinate());
    assertEquals(10, movingEntity.getYCoordinate());
  }

  @Test
  void testPerformActionsPos1(){
    MovementQueue movementQueue = new MovementQueue();
    BasicMovingEntity character = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.moveractions.basicmovement.RightMovement", "MoverAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);

    assertEquals(0, character.getXCoordinate());
  }

  @Test
  void testPerformActionsPos2(){
    MovementQueue movementQueue = new MovementQueue();
    BasicMovingEntity character = new BasicMovingEntity(null,0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    List<ActionData> actionList = new ArrayList<>();
    List<String> params = new ArrayList<>();
    ActionData data = new ActionData("ooga.model.actions.moveractions.Bounce", "MoverAction", params);
    actionList.add(data);
    ActionDataContainer container = new ActionDataContainer(actionList);

    assertEquals(0, character.getYCoordinate());
  }

}
