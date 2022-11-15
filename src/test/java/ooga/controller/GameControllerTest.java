package ooga.controller;

import ooga.model.entities.containers.EntityContainer;
import ooga.view.nodes.NodeContainer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    GameController controller;

    @Test
    public void constructorTest(){
        controller = new GameController(Path.of("something"), Path.of("something else"));
        assertInstanceOf(controller.getClass(), GameController.class);
    }

    @Test
    public void stepTest() throws IOException, ParseException {
        controller = new GameController(Path.of("something"), Path.of("something else"));
        NodeContainer nodes = controller.step();

        JSONInformationDecoder jsoner = new JSONInformationDecoder();
        FileReader infoFile = new FileReader("something");
        JSONObject initialGameStates = (JSONObject) new JSONParser().parse(infoFile);
        EntityContainer container = jsoner.makeEntityContainerFromLevelJSON(initialGameStates);

        assertEquals(nodes.size(), container.getContainerSize());
    }

}
