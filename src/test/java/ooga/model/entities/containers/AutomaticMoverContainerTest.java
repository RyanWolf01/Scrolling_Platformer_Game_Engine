package ooga.model.entities.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.entities.characters.AutomaticMovingCharacter;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.Test;

public class AutomaticMoverContainerTest {

  @Test
  void testMoveAllPos1(){

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    AutomaticMovingCharacter mover1 = new AutomaticMovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    AutomaticMoverContainer container = new AutomaticMoverContainer(mover1);

    container.moveAll();

    assertEquals(5, mover1.getXCoordinate());
  }

  @Test
  void testMoveAllPos2(){
    AutomaticMoverContainer container = new AutomaticMoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    AutomaticMovingCharacter mover1 = new AutomaticMovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);
    AutomaticMovingCharacter mover2 = new AutomaticMovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover2);

    container.moveAll();

    assertEquals(5, mover1.getXCoordinate());
    assertEquals(5, mover2.getXCoordinate());
  }

  @Test
  void testMoveAllNeg(){
    AutomaticMoverContainer container = new AutomaticMoverContainer();

    MovementQueue movementQueue = new MovementQueue();
    movementQueue.addMove(new RightMovement());

    AutomaticMovingCharacter mover1 = new AutomaticMovingCharacter(null,0, 0, 0, 0, null, movementQueue);
    container.addMover(mover1);

    assertEquals(0, mover1.getXCoordinate());
  }


}
