package ooga.model.entities.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.characters.maincharacters.Mario;
import ooga.model.entities.movement.Mover;
import org.junit.jupiter.api.Test;

public class MoverContainerTest {

  @Test
  void testGetEntity() {
    Mover mover = new Mario(0, 0, 0, 0, null);
    MoverContainer container = new MoverContainer(mover);
    assertEquals(mover, container.getMover(0));
  }

  @Test
  void testAddEntity() {
    Mover mover = new Mario(0, 0, 0, 0, null);
    MoverContainer container = new MoverContainer(mover);
    container.addMover(mover);
    assertEquals(mover, container.getMover(0));
  }

  @Test
  void testMoveAll(){
    Mover mover = new Mario(0, 0, 0, 0, null);
    mover.changeVelocities(1,1);
    MoverContainer container = new MoverContainer(mover);
    container.addMover(mover);

    container.moveAll();

    assertEquals(1, container.getMover(0).getXVelocity());
    assertEquals(1, container.getMover(0).getYVelocity());
  }

}
