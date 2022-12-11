package ooga.model.entities.extrainterfaces;

import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.maincharacteractions.MainCharacterAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

/**
 * User controllable interface so that a character can receive
 * inputs from the user
 */
public interface UserControllable extends Mover, Alive {
    void acceptMoveAction(MoverAction action);
    void acceptAliveAction(AliveAction action);
}
