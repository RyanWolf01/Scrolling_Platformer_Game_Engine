package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.modelactions.EndGameAction;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.modelcallers.GameEnder;

public class EndGameActionParser {
  public static final String ACTION_INTERFACE_NAME = "EndGameAction";

  private final ActionDataContainer myActionDataContainer;

  /**
   * Returns a new EndGameActionParser instantiated with the ActionDataContainer passed
   *
   * @param actionDataContainer the ActionDataContainer with ActionData to be parsed
   */
  public EndGameActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parse all EndGameActions from this EndGameActionParser's ActionDataContainer and apply them to the
   * GameEnder entity passed. Throws an ActionParsingException if an error occurs. Returns the number of
   * EndGameActions applied to the GameEnder specified.
   *
   * @param gameEnder GameEnder entity to be executed on
   * @return number of MoverActionsApplied.
   */
  public int parseAndApplyActions(GameEnder gameEnder) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(gameEnder);
        numActionsExecuted += 1;
      }
    }

    return numActionsExecuted;
  }

  private EndGameAction parseAction(ActionData actionData) {
    // Make sure the actionData has no params
    if (actionData.params().size() != 0) {
      throw new ActionParsingException(
          "EndGameActions do not have any params. Invalid ActionData provided:"
              + actionData.toString());
    }

    // Parse the actionData and return the correct AliveAction instance, or throw exception
    try {
      Class<?> clazz = Class.forName(actionData.className());
      return (EndGameAction) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {

      throw new ActionParsingException(
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed: "
              + actionData.toString(), e);
    }

  }
}