package ooga.model.collisions.actiondata;

import java.util.Collection;
import java.util.Iterator;

public class ActionDataContainer implements Iterable<ActionData> {
  private final Collection<ActionData> myActionData;

  public ActionDataContainer(Collection<ActionData> actionData) {
    myActionData = actionData;
  }

  @Override
  public Iterator<ActionData> iterator() {
    return myActionData.iterator();
  }

}
