package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.entities.containers.EntityContainer;
import ooga.view.ViewInfo;
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
import java.util.Map;

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

    @Test
    public void highScores(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "sprint_1_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        Map<String, Integer> map = controller.getHighScores();

        assertEquals(map.get("test"), 459);
    }

    @Test
    public void viewInfo(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "movement_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        ViewInfo info = controller.getViewInfo();

        assertEquals(100, info.marginLeft());
        assertEquals(400, info.marginRight());
        assertEquals(200, info.marginBottom());
        assertEquals(200, info.marginTop());

        assertEquals("bliss.jpg", info.backgroundURL());
    }

    @Test
    public void stepTest(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "movement_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        assertEquals(0, controller.getPlayerScore());

        controller.step();

        assertEquals(0, controller.getPlayerScore());
    }

    @Test
    public void isRunningTest(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "movement_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        assertTrue(controller.isGameRunning());
    }

    @Test
    public void keyInputTest(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "movement_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        controller.handleKeyInput(KeyCode.D);

        assertTrue(controller.isGameRunning());
    }

    @Test
    public void livesTest(){
        String resourcesDirectory = System.getProperty("user.dir") + slash + "src" + slash + "test" + slash + "resources" + slash;
        String levelDirectory = resourcesDirectory + "movement_test" + slash;
        controller = new GameController(levelDirectory + "level.json", levelDirectory + "collisions.json", levelDirectory + "controls.json");

        assertEquals(2, controller.getMainCharacterLives());
    }



}
