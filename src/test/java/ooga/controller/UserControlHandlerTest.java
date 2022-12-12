package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.controller.exceptions.MalformedJSONException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserControlHandlerTest {
    UserControlHandler controlHandler;

    @Test
    public void addMoveActionTest(){
        controlHandler = new UserControlHandler();

        controlHandler.addControl("W", "move_up");
        controlHandler.addControl("RIGHT", "bounce");

        assertTrue(controlHandler.isMoveAction(KeyCode.W));
        assertTrue(controlHandler.isMoveAction(KeyCode.RIGHT));

        assertFalse(controlHandler.isAliveAction(KeyCode.W));
        assertFalse(controlHandler.isAliveAction(KeyCode.RIGHT));
    }

    @Test
    public void addAliveActionTest(){
        controlHandler = new UserControlHandler();

        controlHandler.addControl("W", "kill");
        controlHandler.addControl("RIGHT", "increase_life");

        assertTrue(controlHandler.isAliveAction(KeyCode.W));
        assertTrue(controlHandler.isAliveAction(KeyCode.RIGHT));

        assertFalse(controlHandler.isAliveAction(KeyCode.W));
        assertFalse(controlHandler.isAliveAction(KeyCode.RIGHT));
    }

    @Test
    public void badActionTest(){
        controlHandler = new UserControlHandler();

        assertThrows(MalformedJSONException.class, () -> {
            controlHandler.addControl("W", "killdsvcs");
        });

        assertThrows(MalformedJSONException.class, () -> {
            controlHandler.addControl("bbbb", "killdsvcs");
        });

    }
}
