package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.maincharacteractions.MainCharacterAction;
import ooga.model.actions.modelactions.EndGameAction;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.entitymodels.MainCharacter;
import ooga.model.entities.modelcallers.GameEnder;

public class MainCharacterActionParser {
  public static final String ACTION_INTERFACE_NAME = "MainCharacterAction";

  private final ActionDataContainer myActionDataContainer;

  /**
   * Returns a new EndGameActionParser instantiated with the ActionDataContainer passed
   *
   * @param actionDataContainer the ActionDataContainer with ActionData to be parsed
   */
  public MainCharacterActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parse all EndGameActions from this EndGameActionParser's ActionDataContainer and apply them to the
   * GameEnder entity passed. Throws an ActionParsingException if an error occurs. Returns the number of
   * EndGameActions applied to the GameEnder specified.
   *
   * @param mainCharacter GameEnder entity to be executed on
   * @return number of MoverActionsApplied.
   */
  public int parseAndApplyActions(MainCharacter mainCharacter) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(mainCharacter);
        numActionsExecuted += 1;
      }
    }

    return numActionsExecuted;
  }

  private MainCharacterAction parseAction(ActionData actionData) {
    // Make sure the actionData has no params
    if (actionData.params().size() != 0) {
      throw new ActionParsingException(
          "EndGameActions do not have any params. Invalid ActionData provided:"
              + actionData.toString());
    }

    // Parse the actionData and return the correct AliveAction instance, or throw exception
    try {
      Class<?> clazz = Class.forName(actionData.className());
      return (MainCharacterAction) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {

      throw new ActionParsingException(
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed: "
              + actionData.toString(), e);
    }

  }
}