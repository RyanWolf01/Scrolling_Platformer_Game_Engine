package ooga.model.collisions.actiondata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class that encapsulates a collection of ActionData objects. To be returned by
 * a CollisionChart
 */
public class ActionDataContainer implements Iterable<ActionData> {
  private final Collection<ActionData> myActionData;

  /**
   * Get a new ActionDataContainer, pass in Collection of ActionData
   * @param actionDataCollection Collection of ActionData to add
   */
  public ActionDataContainer(Collection<ActionData> actionDataCollection) {
    myActionData = actionDataCollection;
  }

  /**
   * Create new empty ActionDataContainer
   */
  public ActionDataContainer() {
    myActionData = new ArrayList<>();
  }

  /**
   * Return the size of the ActionData container
   * @return size
   */
  public int size() {
    return myActionData.size();
  }

  /**
   * Add an ActionData object to the container
   * @param actionData ActionData object to be added
   */
  public void addActionData(ActionData actionData) {
    myActionData.add(actionData);
  }

  /**
   * Returns the iterator for this ActionDataContainer
   * @return Iterator
   */
  @Override
  public Iterator<ActionData> iterator() {
    return myActionData.iterator();
  }

}
