package ooga.model.entities.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.modelcallers.GameEnder;

public class GameEnderContainer implements Iterable<GameEnder> {
  private List<GameEnder> gameEnders;
  /**
   * default constructor
   */
  public GameEnderContainer(){
    gameEnders = new ArrayList<>();
  }


  /**
   * Add CollidableEntity to the container
   */
  public void addGameEnder(GameEnder entity){
    gameEnders.add(entity);
  }

  public boolean contains(Entity entity){
    return gameEnders.contains(entity);
  }

  @Override
  public Iterator<GameEnder> iterator() {
    return gameEnders.iterator();
  }
}
