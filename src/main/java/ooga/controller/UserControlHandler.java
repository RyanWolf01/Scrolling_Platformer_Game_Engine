package ooga.controller;

import javafx.scene.input.KeyCode;
import ooga.controller.exceptions.MalformedJSONException;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.aliveactions.AliveActionGetter;
import ooga.model.actions.moveractions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This is used to connect the users keyboard inputs to actions for the main character to perform
 */
public class UserControlHandler {
    private Map<KeyCode, MoverAction> moveActionMap;
    private Map<KeyCode, AliveAction> aliveActionMap;
    private final AliveActionGetter aliveActionGetter;
    private final MoverActionGetter moverActionGetter;

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
            throw new MalformedJSONException("invalid_action");
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

}
