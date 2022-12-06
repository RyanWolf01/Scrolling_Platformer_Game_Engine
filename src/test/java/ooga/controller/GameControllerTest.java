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
    String slash = System.getProperty("file.separator");

    @Test
    public void getLevelDirectoryTest(){
      String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
      String levelDirectory = resourcesDirectory + "sprint_1_test" + slash;
      controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");
      assertEquals(levelDirectory, controller.getLevelDirectory());
    }

    /*
    @Test
    public void constructorTest(){
        controller = new GameController("something", "something else", "something else again");
        assertInstanceOf(controller.getClass(), GameController.class);
    }

    @Test
    public void stepTest() throws IOException, ParseException {
        ConnectionContainer connectionContainer = new ConnectionContainer();
        controller = new GameController("something", "something else", "something else again");
        NodeContainer nodes = controller.step();

        JSONInformationDecoder jsoner = new JSONInformationDecoder();
        FileReader infoFile = new FileReader("something");
        JSONObject initialGameStates = (JSONObject) new JSONParser().parse(infoFile);
        jsoner.makeEntityContainerFromLevelJSON("something", connectionContainer);

         assertEquals(nodes.size(), container.getContainerSize());
    }
     */

}
