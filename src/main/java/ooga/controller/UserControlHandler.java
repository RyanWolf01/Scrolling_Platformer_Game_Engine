package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.actions.aliveactions.Kill;
import ooga.model.actions.moveractions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This is used to connect the users keyboard inputs to actions for the main character to perform
 */
public class UserControlHandler {
    private Map<KeyCode, MoverAction> moveActionMap;
    private Map<KeyCode, AliveAction> aliveActionMap;
    private Map<String, AliveAction> allAliveActions;
    private Map<String, MoverAction> allMoverActions;

    public UserControlHandler(){
        moveActionMap = new HashMap<>();
        aliveActionMap = new HashMap<>();
        setUpActions();
    }

    /**
     * Add control for the game to the maps
     * @param keyCodeName string that maps to KeyCode
     * @param action string that maps to Action
     */
    public void addControl(String keyCodeName, String action){
        KeyCode code = KeyCode.valueOf(keyCodeName);
        if(allAliveActions.containsKey(action)){ //check if keyCodeName is an alive action
            aliveActionMap.put(code, allAliveActions.get(action));
        }
        else if(allMoverActions.containsKey(action)){ //check if keyCodeName is mover action
            moveActionMap.put(code, allMoverActions.get(action));
        }
        else{
            // throw an exception, not a valid action
        }
    }

    private void setUpActions(){
        allAliveActions = new HashMap<>();
        allMoverActions = new HashMap<>();

        allAliveActions.put("kill", new Kill());
        allAliveActions.put("increase_life", new IncreaseLife());

        allMoverActions.put("bounce", new Bounce());
        allMoverActions.put("stop_both_directions", new StopBothDirections());
        allMoverActions.put("stop_x_movement", new StopXMovement());
        allMoverActions.put("stop_y_movement", new StopYMovement());
    }

}
