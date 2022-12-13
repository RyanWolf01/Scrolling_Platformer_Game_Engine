package ooga.model.entities.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;


import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MainCharacter;
import ooga.model.entities.movement.MovementQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityContainerTest {
  public EntityContainer container;

  @BeforeEach
  public void setup(){
    container = new EntityContainer();
  }

  @Test
  public void testConstructor() {
    assertEquals(container.getClass(), EntityContainer.class);
  }

  @Test
  public void addTest(){
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, new MovementQueue());

    container.addEntity(e);

    assert(container.contains(e));
  }

  @Test
  public void iteratorTest(){
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, new MovementQueue());

    container.addEntity(e);
    Entity testEntity = container.iterator().next();
    assertEquals(e, testEntity);
  }
}
