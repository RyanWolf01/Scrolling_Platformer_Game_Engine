package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.aliveactions.AliveActionGetter;
import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.actions.aliveactions.Kill;
import ooga.model.actions.moveractions.*;

import java.util.HashMap;
import java.util.Map;
import ooga.model.actions.moveractions.basicmovement.DownwardMovement;
import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.actions.moveractions.basicmovement.UpwardMovement;

/**
 * This is used to connect the users keyboard inputs to actions for the main character to perform
 */
public class UserControlHandler {
    private Map<KeyCode, MoverAction> moveActionMap;
    private Map<KeyCode, AliveAction> aliveActionMap;
    private AliveActionGetter aliveActionGetter;
    private MoverActionGetter moverActionGetter;
    //private Map<String, AliveAction> allAliveActions;
    //private Map<String, MoverAction> allMoverActions;

    public UserControlHandler(){
        aliveActionGetter = new AliveActionGetter();
        moverActionGetter = new MoverActionGetter();
        moveActionMap = new HashMap<>();
        aliveActionMap = new HashMap<>();
    }

    /**
     * Add control for the game to the maps
     * @param keyCodeName string that maps to KeyCode
     * @param action string that maps to Action
     */
    public void addControl(String keyCodeName, String action){
        KeyCode code = KeyCode.valueOf(keyCodeName);
        if(aliveActionGetter.isAliveAction(action)){ //check if keyCodeName is an alive action
            aliveActionMap.put(code, aliveActionGetter.aliveActionTranslate(action));
        }
        else if(moverActionGetter.isMoverAction(action)){ //check if keyCodeName is mover action
            moveActionMap.put(code, moverActionGetter.moverActionTranslate(action));
        }
        else{
            // throw an exception, not a valid action
        }
    }

    public boolean isMoveAction(KeyCode code){
        return moveActionMap.containsKey(code);
    }

    public boolean isAliveAction(KeyCode code){
        return aliveActionMap.containsKey(code);
    }

    public MoverAction getMoverAction(KeyCode code){
        return moveActionMap.get(code);
    }

    public AliveAction getAliveAction(KeyCode code){
        return aliveActionMap.get(code);
    }

//    private void setUpActions(){
//        allAliveActions = new HashMap<>();
//        allMoverActions = new HashMap<>();
//
//        allAliveActions.put("kill", new Kill());
//        allAliveActions.put("increase_life", new IncreaseLife());
//
//        allMoverActions.put("bounce", new Bounce());
//        allMoverActions.put("stop_both_directions", new StopBothDirections());
//        allMoverActions.put("stop_x_movement", new StopXMovement());
//        allMoverActions.put("stop_y_movement", new StopYMovement());
//        allMoverActions.put("move_left", new LeftMovement());
//        allMoverActions.put("move_right", new RightMovement());
//        allMoverActions.put("move_down", new DownwardMovement());
//        allMoverActions.put("move_up", new UpwardMovement());
//    }

}
