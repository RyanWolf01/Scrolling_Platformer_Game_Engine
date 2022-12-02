package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.entities.livingentities.Alive;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;

/**
 * Takes an ActionDataContainer and uses reflection to parse AliveActions from them. Then applies
 * these AliveActions to an Alive character.
 */
public class AliveActionParser {

  public static final String ACTION_INTERFACE_NAME = "AliveAction";

  private final ActionDataContainer myActionDataContainer;

  /**
   * Returns a new AliveActionParser instantiated with the ActionDataContainer passed
   *
   * @param actionDataContainer the ActionDataContainer with ActionData to be parsed
   */

  public AliveActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parse all AliveActions from this AliveActionParser's ActionDataContainer and apply them to the
   * Alive entity passed. Throws an ActionParsingException if an error occurs. Returns the number of
   * AliveActions applied to the alive entity specified.
   *
   * @param alive Alive entity to be executed on
   * @return number of AliveActionsApplied.
   */
  public int parseAndApplyActions(Alive alive) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(alive);
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
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed: "
              + actionData.toString(), e);
    }

  }

}
