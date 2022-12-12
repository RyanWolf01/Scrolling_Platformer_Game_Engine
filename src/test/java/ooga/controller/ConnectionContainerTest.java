package ooga.controller;

import javafx.scene.Node;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.info.EntityInfo;
import ooga.view.nodes.ScrollingNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionContainerTest {
    public ConnectionContainer container;
    public JSONInformationDecoder decoder = new JSONInformationDecoder("data/games/sprint_1_test/level.json", "data/games/sprint_1_test/collisions.json", "data/games/sprint_1_test/controls.json");

    @Test
    public void constructorTest(){
        container = new ConnectionContainer(decoder);
        assertInstanceOf(container.getClass(), ConnectionContainer.class);
    }

    @Test
    public void testAdd(){
        container = new ConnectionContainer(decoder);
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);

        assertEquals(1, container.viewables().size());
    }

    @Test
    public void testMultipleAdds(){
        container = new ConnectionContainer(decoder);
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);
        assertEquals(1, container.viewables().size());
        container.addNewEntity(0,0,0,0,"uh", info);
        assertEquals(2, container.viewables().size());
    }

    @Test
    public void testMap(){
        container = new ConnectionContainer(decoder);
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);

        for(ScrollingNode node : container.viewables()){
            Entity entity = container.getConnectedEntity(node);

            assertEquals(entity.getXCoordinate(), 0);
            assertEquals(entity.getYCoordinate(), 0);
            assertEquals(entity.getImmutableEntityInfo(), info);
        }
    }

    @Test
    public void testUpdate(){
        container = new ConnectionContainer(decoder);
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);

        for(Node node : container.viewables()){
            Entity entity = container.getConnectedEntity((ScrollingNode) node);

            entity.setXCoordinate(20);
            entity.setYCoordinate(10);
        }

        container.update();

        for(Node node : container.viewables()){
            Entity entity = container.getConnectedEntity((ScrollingNode) node);

            assertEquals(entity.getXCoordinate(), 20);
            assertEquals(entity.getYCoordinate(), 10);
        }
    }
}
