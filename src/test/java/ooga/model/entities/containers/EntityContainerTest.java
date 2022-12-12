package ooga.model.entities.containers;

import static org.junit.jupiter.api.Assertions.assertEquals;

<<<<<<< HEAD
import ooga.model.entities.Entity;
import ooga.model.entities.deadmovingentities.MovementQueue;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacter;
=======
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MainCharacter;
>>>>>>> master
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
<<<<<<< HEAD
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, new MovementQueue());
=======
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, null);
>>>>>>> master
    container.addEntity(e);

    assert(container.contains(e));
  }

  @Test
  public void iteratorTest(){
<<<<<<< HEAD
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, new MovementQueue());
=======
    Entity e = new MainCharacter(null, 0, 0, 0, 0 , null, null);
>>>>>>> master
    container.addEntity(e);
    Entity testEntity = container.iterator().next();
    assertEquals(e, testEntity);
  }
}
