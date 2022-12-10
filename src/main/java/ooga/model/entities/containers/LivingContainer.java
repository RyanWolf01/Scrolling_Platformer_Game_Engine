package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.deadmovingentities.AutomaticMover;
import ooga.model.entities.livingentities.Alive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LivingContainer implements Iterable<Alive> {
  Logger LOG = LogManager.getLogger(LivingContainer.class);

  List<Alive> livers;

  public LivingContainer(){
    livers = new ArrayList<>();
  }

  public void addLiver(Alive liver){
    livers.add(liver);
  }

  @Override
  public Iterator<Alive> iterator() {
    return livers.iterator();
  }
}
