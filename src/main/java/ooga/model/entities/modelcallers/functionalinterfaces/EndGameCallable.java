package ooga.model.entities.modelcallers.functionalinterfaces;

@FunctionalInterface
public interface EndGameCallable {
  void execute(boolean userWon);
}
