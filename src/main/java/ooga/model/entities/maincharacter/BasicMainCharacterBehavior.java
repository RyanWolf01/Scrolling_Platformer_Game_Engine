package ooga.model.entities.maincharacter;

import ooga.model.MainCharacterState;
import ooga.model.entities.alive.ImmutableAliveBehavior;

public class BasicMainCharacterBehavior implements MainCharacterBehavior{

  private ImmutableAliveBehavior aliveBehavior;
  public BasicMainCharacterBehavior(ImmutableAliveBehavior aliveBehavior){
    this.aliveBehavior = aliveBehavior;
  }

  private MainCharacterState mainCharacterState = MainCharacterState.RUNNING;

  public MainCharacterState getGameState() {
    return mainCharacterState;
  }

  @Override
  public MainCharacterState getMainCharacterState() {
    return null;
  }

  @Override
  public void checkNumLivesAndUpdateMyGameState() {
    if (aliveBehavior.getLives() <= 0) {
      mainCharacterState = MainCharacterState.USER_LOST;
    }
  }
  @Override
  public void setGameState(MainCharacterState mainCharacterState) {
    this.mainCharacterState = mainCharacterState;
  }

  @Override
  public void updateScore(double addToScore) {

  }

  @Override
  public int getScore() {
    return 0;
  }

}
