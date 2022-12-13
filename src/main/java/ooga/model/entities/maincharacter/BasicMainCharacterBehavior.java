package ooga.model.entities.maincharacter;

import ooga.model.entities.alive.ImmutableAliveBehavior;

public class BasicMainCharacterBehavior implements MainCharacterBehavior{

  private ImmutableAliveBehavior aliveBehavior;
  private MainCharacterState mainCharacterState = MainCharacterState.RUNNING;
  private int score;
  public BasicMainCharacterBehavior(ImmutableAliveBehavior aliveBehavior){
    this.aliveBehavior = aliveBehavior;
    score = 0;
  }

  /**
   *
   * @return main character state
   */
  @Override
  public MainCharacterState getMainCharacterState() {
    return mainCharacterState;
  }

  /**
   * check lives
   */
  @Override
  public void checkLivesAndUpdateMainCharacterState() {
    if (aliveBehavior.getLives() <= 0) {
      mainCharacterState = MainCharacterState.USER_LOST;
    }
  }

  /**
   *
   * @param mainCharacterState new main character state
   */
  @Override
  public void setMainCharacterState(MainCharacterState mainCharacterState) {
    this.mainCharacterState = mainCharacterState;
  }

  /**
   *
   * @param addToScore to add to score
   */
  @Override
  public void updateScore(int addToScore) {
    if(score + addToScore >= 0)
      score += addToScore;
  }

  /**
   *
   * @return score
   */
  @Override
  public int getScore() {
    return score;
  }

}
