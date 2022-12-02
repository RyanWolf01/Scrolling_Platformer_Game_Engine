package ooga.model.entities.containers;

import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.deadmovingentities.AutomaticMover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackendContainerTest {
  public JSONInformationDecoder decoder;
  public BackendContainer container;

  @BeforeEach
  public void setup(){
    decoder = new JSONInformationDecoder("data/games/sprint_1_test/level.json",
                                      "data/games/sprint_1_test/collisions.json",
                                       "data/games/sprint_1_test/controls.json");
    container = new BackendContainer(decoder);
  }

  @Test
  public void addCollidableSimpleTest(){
    EntityInfo info = new EntityInfo("mario");
    info.set("texture", "idk");
    container.addNewEntity(0, 0, 0, 0, "mario", info);

    int collidableCounter = 0;
    for(CollidableEntity c : container.collidables()){
      collidableCounter++;
    }

    assertEquals(collidableCounter, 1);
  }

  @Test
  public void addAutoMoverCollidableTest(){
    EntityInfo info = new EntityInfo("goomba");
    info.set("texture", "idk");
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
    EntityInfo info = new EntityInfo("static_entity");
    info.set("texture", "idk");
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
}
