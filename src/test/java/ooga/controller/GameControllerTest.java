package ooga.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    GameController controller;

    @BeforeEach
    private void setup(){
        controller = new GameController();
    }

    @Test
    private void constructorTest(){
        assertInstanceOf(controller.getClass(), GameController.class);
    }

}
