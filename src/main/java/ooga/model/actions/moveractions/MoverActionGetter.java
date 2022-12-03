package ooga.model.actions.moveractions;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.aliveactions.IncreaseLife;
import ooga.model.actions.aliveactions.Kill;
import ooga.model.actions.moveractions.basicmovement.DownwardMovement;
import ooga.model.actions.moveractions.basicmovement.LeftMovement;
import ooga.model.actions.moveractions.basicmovement.RightMovement;
import ooga.model.actions.moveractions.basicmovement.UpwardMovement;

import java.util.HashMap;
import java.util.Map;

public class MoverActionGetter {
    private Map<String, MoverAction> allMoverActions;

    public MoverActionGetter(){
        allMoverActions = new HashMap<>();

        allMoverActions.put("bounce", new Bounce());
        allMoverActions.put("stop_both_directions", new StopBothDirections());
        allMoverActions.put("stop_x_movement", new StopXMovement());
        allMoverActions.put("stop_y_movement", new StopYMovement());
        allMoverActions.put("move_left", new LeftMovement());
        allMoverActions.put("move_right", new RightMovement());
        allMoverActions.put("move_down", new DownwardMovement());
        allMoverActions.put("move_up", new UpwardMovement());
    }

    public MoverAction moverActionTranslate(String type){
        if(isMoverAction(type)) {
            return allMoverActions.get(type);
        }
        return null;
    }

    public boolean isMoverAction(String type){
        return allMoverActions.containsKey(type);
    }
}
