package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.collisions.data.ActionData;
import ooga.model.collisions.data.ActionDataContainer;
import ooga.model.entities.alive.Alive;
import ooga.model.entities.movement.Mover;

public class AliveActionParser {

  public static final String ACTION_INTERFACE_NAME = "AliveAction";

  private ActionDataContainer myActionDataContainer;

  public AliveActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  public int parseAndApplyActions(Alive aliveEntity) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(aliveEntity);
        numActionsExecuted += 1;
      }
    }

    return numActionsExecuted;
  }

  private AliveAction parseAction(ActionData actionData) {
    // Make sure the actionData has no params
    if (actionData.params().size() != 0) {
      throw new ActionParsingException(
          "AliveActions do not have any params. Invalid ActionData provided:"
              + actionData.toString());
    }

    // Parse the actionData and return the correct AliveAction instance, or throw exception
    try {
      Class<?> clazz = Class.forName(actionData.className());
      return (AliveAction) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {

      throw new ActionParsingException(
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed:"
              + actionData.toString(), e);
    }

  }

}
