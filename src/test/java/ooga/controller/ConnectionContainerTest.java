package ooga.controller;

import javafx.scene.Node;
import ooga.model.entities.Entity;
import ooga.model.entities.data.EntityInfo;
import ooga.view.nodes.ScrollingNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionContainerTest {
    public ConnectionContainer container;

    @Test
    public void constructorTest(){
        container = new ConnectionContainer();
        assertInstanceOf(container.getClass(), ConnectionContainer.class);
    }

    public void testAdd(){
        container = new ConnectionContainer();
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);

        assertEquals(1, container.viewables().size());
    }

    public void testMultipleAdds(){
        container = new ConnectionContainer();
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);
        assertEquals(1, container.viewables().size());
        container.addNewEntity(0,0,0,0,"uh", info);
        assertEquals(2, container.viewables().size());
    }

    public void testMap(){
        container = new ConnectionContainer();
        EntityInfo info = new EntityInfo("uh");
        info.set("texture", "idk");
        container.addNewEntity(0,0,0,0,"uh", info);

        for(Node node : container.viewables()){
            Entity entity = container.getConnectedEntity((ScrollingNode) node);

            assertEquals(entity.getXCoordinate(), 0);
            assertEquals(entity.getYCoordinate(), 0);
            assertEquals(entity.getImmutableEntityInfo(), info);
        }
    }

    public void testUpdate(){
        container = new ConnectionContainer();
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
