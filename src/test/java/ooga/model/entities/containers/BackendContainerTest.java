package ooga.model.entities.containers;

import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.AutomaticMovingEntity;
import ooga.model.entities.CollidableEntity;
import ooga.model.entities.data.EntityInfo;
import ooga.model.entities.movement.AutomaticMover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackendContainerTest {
  public JSONInformationDecoder decoder;
  public BackendContainer container;

  @BeforeEach
  public void setup(){
    decoder = new JSONInformationDecoder("uh", "idk", "does it matter");
    container = new BackendContainer(decoder);
  }

  @Test
  public void constructorTest(){
    assertInstanceOf(container.getClass(), BackendContainer.class);
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
    EntityInfo info = new EntityInfo("?1!");
    info.set("texture", "idk");
    container.addNewEntity(0, 0, 0, 0, "?1!", info);

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
