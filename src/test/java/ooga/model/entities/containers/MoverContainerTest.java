package ooga.model.entities.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ResourceBundle;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.movement.MovementQueue;
import ooga.model.entities.movement.Mover;
import org.junit.jupiter.api.Test;

public class MoverContainerTest {

  @Test
  void testMoveAllPos1(){

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    MoverContainer container = new MoverContainer();
    container.addMover(mover1);

    container.moveAll();

    assertEquals( Integer.parseInt(ResourceBundle.getBundle("properties/movement").getString("right_velocity"))
        , mover1.getXCoordinate());
  }

  @Test
  void testMoveAllPos2(){
    MoverContainer container = new MoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);
    MovingCharacter mover2 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover2);

    container.moveAll();

    assertEquals( Integer.parseInt(ResourceBundle.getBundle("properties/movement").getString("right_velocity"))
        , mover1.getXCoordinate());
    assertEquals( Integer.parseInt(ResourceBundle.getBundle("properties/movement").getString("right_velocity"))
        , mover2.getXCoordinate());
  }

  @Test
  void testMoveAllNeg(){
    MoverContainer container = new MoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);

    assertEquals(0, mover1.getXCoordinate());
  }

  @Test
  void testResetVelocitiesPos1(){

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    MoverContainer container = new MoverContainer();
    container.addMover(mover1);

    container.moveAll();

    container.resetVelocities(true, false);


    assertEquals(0 , mover1.getXVelocity());
    assertEquals(0 , mover1.getYVelocity());
  }

  @Test
  void testResetVelocitiesPos2(){
    MoverContainer container = new MoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);
    MovingCharacter mover2 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover2);

    container.moveAll();

    container.resetVelocities(true, false);

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    assertEquals(0 , mover1.getXVelocity());
    assertEquals(0 , mover2.getXVelocity());
    assertEquals(gravityVelocity , mover1.getYVelocity());
    assertEquals(gravityVelocity , mover2.getYVelocity());
  }

  @Test
  void testResetVelocitiesNegative(){
    MoverContainer container = new MoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    MovingCharacter mover1 = new MovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);
    container.moveAll();
    container.resetVelocities(false,false);

    double gravityVelocity = Double.parseDouble(
        ResourceBundle.getBundle("properties/movement").getString("gravity_velocity"));

    assertEquals(Integer.parseInt(ResourceBundle.getBundle("properties/movement").getString("right_velocity")), mover1.getXCoordinate());
    assertEquals(gravityVelocity, mover1.getYCoordinate());
  }




}
