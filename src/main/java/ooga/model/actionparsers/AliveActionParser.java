package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.collisions.data.ActionData;
import ooga.model.collisions.data.ActionDataContainer;

public class AliveActionParser {

  public static final String ACTION_INTERFACE_NAME = "AliveAction";

  private ActionDataContainer myActionDataContainer;

  public AliveActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  public AliveAction getAction() {
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        return parseAction(actionData);
      }
    }

    throw new ActionParsingException("getAction could not find an action of type AliveAction");
  }

  public boolean hasAction() {
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        return true;
      }
    }
    return false;
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
