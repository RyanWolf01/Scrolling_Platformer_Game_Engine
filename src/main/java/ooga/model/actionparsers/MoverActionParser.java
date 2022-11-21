package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.data.ActionData;
import ooga.model.collisions.data.ActionDataContainer;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

public class MoverActionParser {

  public static final String ACTION_INTERFACE_NAME = "MoverAction";

  private final ActionDataContainer myActionDataContainer;

  public MoverActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parses MoverActions encoded in actionDataContainer and applies them to the mover param
   * @param mover the mover on which an action will be applied
   * @return num actions applied to mover
   */
  public int parseAndApplyActions(Mover mover) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(mover);
        numActionsExecuted += 1;
      }
    }

    return numActionsExecuted;
  }

  private MoverAction parseAction(ActionData actionData) {
    // Make sure the actionData has no params
    if (actionData.params().size() != 0) {
      throw new ActionParsingException(
          "AliveActions do not have any params. Invalid ActionData provided:"
              + actionData.toString());
    }

    // Parse the actionData and return the correct AliveAction instance, or throw exception
    try {
      Class<?> clazz = Class.forName(actionData.className());
      return (MoverAction) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {

      throw new ActionParsingException(
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed:"
              + actionData.toString(), e);
    }

  }
}
