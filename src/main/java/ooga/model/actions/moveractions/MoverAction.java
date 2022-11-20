package ooga.model.actions.moveractions;

import ooga.model.entities.movement.Mover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MoverAction {
  List<String> MOVER_ACTIONS = new ArrayList<>(Arrays.asList("move_left", "move_right"));
  void execute(Mover entity);

}
