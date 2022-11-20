package ooga.model.actions.aliveactions;

import ooga.model.entities.alive.Alive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AliveAction {
  List<String> ALIVE_ACTIONS = new ArrayList<>(Arrays.asList("kill", "add_life"));
  void execute(Alive entity);
}
