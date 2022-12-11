package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.basicentityactions.BasicEntityAction;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.entitymodels.MainCharacter;

public class EntityActionParser {
  public static final String ACTION_INTERFACE_NAME = "BasicEntityAction";

  private final ActionDataContainer myActionDataContainer;

  /**
   * Returns a new EndGameActionParser instantiated with the ActionDataContainer passed
   *
   * @param actionDataContainer the ActionDataContainer with ActionData to be parsed
   */
  public EntityActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parse all BasicEntity actions from ActionDataContainer and apply them to the
   *  entity passed.
   *
   * @param entity entity to be executed on
   * @return number of actions applied.
   */
  public int parseAndApplyActions(Entity entity) {
    int numActionsExecuted = 0;
    for (ActionData actionData : myActionDataContainer) {
      if (actionData.interfaceName().equals(ACTION_INTERFACE_NAME)) {
        parseAction(actionData).execute(entity);
        numActionsExecuted += 1;
      }
    }

    return numActionsExecuted;
  }

  private BasicEntityAction parseAction(ActionData actionData) {
    // Make sure the actionData has no params
    if (actionData.params().size() != 0) {
      throw new ActionParsingException(
          "EndGameActions do not have any params. Invalid ActionData provided:"
              + actionData.toString());
    }

    // Parse the actionData and return the correct AliveAction instance, or throw exception
    try {
      Class<?> clazz = Class.forName(actionData.className());
      return (BasicEntityAction) clazz.getDeclaredConstructor().newInstance();
    } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {

      throw new ActionParsingException(
          "Parsing of ActionData did not work properly. The ActionData attempted to be parsed: "
              + actionData.toString(), e);
    }

  }
}