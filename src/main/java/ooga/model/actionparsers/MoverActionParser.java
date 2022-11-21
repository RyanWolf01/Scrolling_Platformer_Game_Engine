package ooga.model.actionparsers;

import java.lang.reflect.InvocationTargetException;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.movement.Mover;
import ooga.model.collisions.actiondata.ActionData;
import ooga.model.collisions.actiondata.ActionDataContainer;


/**
 * Takes an ActionDataContainer and uses reflection to parse AliveActions from them. Then applies
 * these AliveActions to an Alive character.
 */
public class MoverActionParser {

  public static final String ACTION_INTERFACE_NAME = "MoverAction";

  private final ActionDataContainer myActionDataContainer;

  /**
   * Returns a new MoverActionParser instantiated with the ActionDataContainer passed
   *
   * @param actionDataContainer the ActionDataContainer with ActionData to be parsed
   */
  public MoverActionParser(ActionDataContainer actionDataContainer) {
    myActionDataContainer = actionDataContainer;
  }

  /**
   * Parse all MoveActions from this MoveActionParser's ActionDataContainer and apply them to the
   * Mover entity passed. Throws an ActionParsingException if an error occurs. Returns the number of
   * MoverActions applied to the Mover specified.
   *
   * @param mover Mover entity to be executed on
   * @return number of MoverActionsApplied.
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
