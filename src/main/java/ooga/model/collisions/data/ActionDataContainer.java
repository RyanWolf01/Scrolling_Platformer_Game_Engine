package ooga.model.collisions.data;

import java.util.Collection;
import java.util.Iterator;

public class ActionDataContainer implements Iterable<ActionData> {
  private final Collection<ActionData> myActionData;

  public ActionDataContainer(Collection<ActionData> actionData) {
    myActionData = actionData;
  }

  public int size() {
    return myActionData.size();
  }

  @Override
  public Iterator<ActionData> iterator() {
    return myActionData.iterator();
  }

}
