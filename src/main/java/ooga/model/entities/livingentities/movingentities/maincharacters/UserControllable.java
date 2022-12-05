package ooga.model.entities.livingentities.movingentities.maincharacters;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.livingentities.Alive;
import ooga.model.entities.deadmovingentities.Mover;

/**
 * User controllable interface so that a character can receive
 * inputs from the user
 */
public interface UserControllable extends Mover, Alive {
    void acceptMoveAction(MoverAction action);
    void acceptAliveAction(AliveAction action);
}
