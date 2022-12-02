package ooga.model.actions.aliveactions;

import javafx.scene.input.KeyCode;
import ooga.model.actions.moveractions.MoverAction;

import java.util.Map;

/**
 * Used to get an AliveAction object based on String keyword
 */
public class AliveActionGetter {
    private Map<String, AliveAction> allAliveActions;

    public AliveActionGetter(){
        allAliveActions.put("kill", new Kill());
        allAliveActions.put("increase_life", new IncreaseLife());
    }

    public AliveAction aliveActionTranslate(String type){
        if(isAliveAction(type)){
            return allAliveActions.get(type);
        }
        return null;
    }

    public boolean isAliveAction(String type){
        return allAliveActions.containsKey(type);
    }
}
