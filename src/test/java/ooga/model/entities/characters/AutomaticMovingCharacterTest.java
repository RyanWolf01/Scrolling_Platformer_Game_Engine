package ooga.model.entities.characters;

import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.actions.moveractions.basicmovement.UpwardMovement;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticMovingCharacterTest {

  @Test
  void testIncrementXVelocityPositive1() {

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(5,0);
    movingCharacter.move();
    assertEquals(5, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(-100,0);
    movingCharacter.move();
    assertEquals(-100, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementXVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,0);
    movingCharacter.move();
    assertEquals(0, movingCharacter.getXCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,5);
    movingCharacter.move();
    assertEquals(5, movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,-100);
    movingCharacter.move();
    assertEquals(-100, movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementYVelocityNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeVelocities(0,0);
    movingCharacter.move();
    assertEquals(0, movingCharacter.getYCoordinate());
  }

  @Test
  void testIncrementLivesPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(1);
    assertEquals(1, movingCharacter.getLives());
  }

  @Test
  void testIncrementLivesPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(100);
    assertEquals(100, movingCharacter.getLives());
  }

  @Test
  void testIncrementLivesNegative() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(0);
    assertEquals(0, movingCharacter.getLives());
  }

  @Test
  void testDecrementLivesPositive1() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(-1);
    assertEquals(0, movingCharacter.getLives());
  }

  @Test
  void testDecrementLivesPositive2() {
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new LeftMovement());
    movementQueue.addMove(new RightMovement());

    MovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);

    movingCharacter.changeLives(100);
    movingCharacter.changeLives(-1);
    assertEquals(99, movingCharacter.getLives());
  }

  @Test
  void testAutomaticMovePos1(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new RightMovement());

    AutomaticMovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);
    movingCharacter.automaticMove();
    assertEquals(5, movingCharacter.getXCoordinate());
  }

  @Test
  void testAutomaticMovePos2(){
    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());
    movementQueue.addMove(new UpwardMovement());

    AutomaticMovingCharacter movingCharacter = new AutomaticMovingCharacter(0, 0, 2, 2, new EntityInfo("GOOMBA"), movementQueue);
    movingCharacter.automaticMove();
    movingCharacter.automaticMove();
    movingCharacter.automaticMove();
    assertEquals(10, movingCharacter.getXCoordinate());
    assertEquals(5, movingCharacter.getYCoordinate());
  }

}
