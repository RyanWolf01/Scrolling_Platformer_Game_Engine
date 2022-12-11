package ooga.model.entities.containers;

import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.Entity;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.Alive;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackendContainerTest {
  public JSONInformationDecoder decoder;
  public BackendContainer container;

  @Test
  public void addCollidableSimpleTest(){
    decoder = new JSONInformationDecoder("data/games/mario/sprint_1_test/level.json",
            "data/games/mario/sprint_1_test/collisions.json",
            "data/games/mario/sprint_1_test/controls.json");
    container = new BackendContainer(decoder);

    EntityInfo info = new EntityInfo("mario");
    info.set("lives", "1");
    info.set("texture", "idk");
    container.addNewEntity(0, 0, 0, 0, "mario", info);

    int collidableCounter = 0;
    for(CollidableEntity c : container.collidables()){
      collidableCounter++;
    }

    assertEquals(collidableCounter, 1);
  }

  /*
  @Test
  public void addAutoMoverCollidableTest(){
    decoder = new JSONInformationDecoder("data/games/mario/sprint_1_test/level.json",
            "data/games/mario/sprint_1_test/collisions.json",
            "data/games/mario/sprint_1_test/controls.json");
    container = new BackendContainer(decoder);

    EntityInfo info = new EntityInfo("goomba");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "goomba", info);

    int moverCounter = 0;
    for(AutomaticMover c : container.automaticMovers()){
      moverCounter++;
    }

    assertEquals(moverCounter, 1);

    int collidableCounter = 0;
    for(CollidableEntity c : container.collidables()){
      collidableCounter++;
    }

    assertEquals(collidableCounter, 1);
  }


  @Test
  public void addStaticEntityTest(){
    decoder = new JSONInformationDecoder("data/games/mario/sprint_1_test/level.json",
            "data/games/mario/sprint_1_test/collisions.json",
            "data/games/mario/sprint_1_test/controls.json");
    container = new BackendContainer(decoder);

    EntityInfo info = new EntityInfo("static_entity");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "static_entity", info);

    int moverCounter = 0;
    for(AutomaticMover c : container.automaticMovers()){
      moverCounter++;
    }

    assertEquals(moverCounter, 0);

    int collidableCounter = 0;
    for(CollidableEntity c : container.collidables()){
      collidableCounter++;
    }

    assertEquals(collidableCounter, 0);


  }


  @Test
  public void complexAddTest(){
    decoder = new JSONInformationDecoder("data/games/mario/movement_test/level.json",
            "data/games/mario/movement_test/collisions.json",
            "data/games/mario/movement_test/controls.json");
    container = new BackendContainer(decoder);

    EntityInfo info = new EntityInfo("static_entity");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "static_entity", info);

    info = new EntityInfo("goomba");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "goomba", info);

    info = new EntityInfo("mario");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "mario", info);

    info = new EntityInfo("end");
    info.set("texture", "idk");
    info.set("lives", "1");
    container.addNewEntity(0, 0, 0, 0, "end", info);

    int moverCounter = 0;
    for(AutomaticMover c : container.automaticMovers()){
      moverCounter++;
    }

    int collidableCounter = 0;
    for(CollidableEntity c : container.collidables()){
      collidableCounter++;
    }

    int entityCounter = 0;
    for(Entity e : container.entities()){
      entityCounter++;
    }

    int liverCounter = 0;
    for(Alive e : container.livers()){
      liverCounter++;
    }

    assertEquals(1, moverCounter);
    assertEquals(3, collidableCounter);
    assertEquals(4, entityCounter);
    assertEquals(3, liverCounter);
  }
  */

  @Test
  public void badTest(){
    decoder = new JSONInformationDecoder("data/games/malformed_test/level.json",
            "data/games/malformed_test/collisions.json",
            "data/games/malformed_test/controls.json");
    container = new BackendContainer(decoder);

    EntityInfo info = new EntityInfo("mario");
    info.set("texture", "idk");
    container.addNewEntity(0, 0, 0, 0, "mario", info);

    //assertThrows(CollisionChartParsingException.class, () -> container.addNewEntity(0, 0, 0, 0, "static_entity", info));
  }
}
