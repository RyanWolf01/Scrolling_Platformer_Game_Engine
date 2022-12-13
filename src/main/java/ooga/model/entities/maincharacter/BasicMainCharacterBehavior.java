package ooga.model.entities.maincharacter;

import ooga.model.MainCharacterState;
import ooga.model.entities.alive.ImmutableAliveBehavior;

public class BasicMainCharacterBehavior implements MainCharacterBehavior{

  private ImmutableAliveBehavior aliveBehavior;
  private MainCharacterState mainCharacterState = MainCharacterState.RUNNING;
  private double score;
  public BasicMainCharacterBehavior(ImmutableAliveBehavior aliveBehavior){
    this.aliveBehavior = aliveBehavior;
    score = 0;
  }

  @Override
  public MainCharacterState getMainCharacterState() {
    return mainCharacterState;
  }

  @Override
  public void checkLivesAndUpdateMainCharacterState() {
    if (aliveBehavior.getLives() <= 0) {
      mainCharacterState = MainCharacterState.USER_LOST;
    }
  }
  @Override
  public void setMainCharacterState(MainCharacterState mainCharacterState) {
    this.mainCharacterState = mainCharacterState;
  }

  @Override
  public void updateScore(double addToScore) {
    score += addToScore;
  }
  @Override
  public double getScore() {
    return score;
  }

}
