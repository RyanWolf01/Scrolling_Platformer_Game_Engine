package ooga.model.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.controller.JSONInformationDecoder;
import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.entitymodels.MovingCharacter;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class EntityFactoryTest {
    public JSONInformationDecoder decoder;
    EntityFactory factory;

    @Test
    public void basicAutoMoverTest(){
        decoder = new JSONInformationDecoder("data/games/mario/sprint_1_test/level.json",
                "data/games/mario/sprint_1_test/collisions.json",
                "data/games/mario/sprint_1_test/controls.json");

        factory = new EntityFactory(decoder);
        EntityInfo info = new EntityInfo("goomba");
        info.set("texture", "/url");
        info.set("lives","1");
        info.set("character_type", "automatic_mover");

        MovingCharacter character =  factory.makeMover(0,0,0,0,"goomba", info);

        assertEquals(0, character.getXCoordinate());
        assertEquals(0, character.getYCoordinate());
        assertEquals(1, character.getLives());
    }

    @Test
    public void basicMainCharacterTest(){
        decoder = new JSONInformationDecoder("data/games/mario/sprint_1_test/level.json",
                "data/games/mario/sprint_1_test/collisions.json",
                "data/games/mario/sprint_1_test/controls.json");

        factory = new EntityFactory(decoder);
        EntityInfo info = new EntityInfo("mario");
        info.set("texture", "/url");
        info.set("lives","1");
        info.set("character_type", "automatic_mover");

        BasicMainCharacter character =  factory.makeMainCharacter(0,0,0,0,"mario", info);

        assertEquals(0, character.getXCoordinate());
        assertEquals(0, character.getYCoordinate());
        assertEquals(1, character.getLives());
    }
}
