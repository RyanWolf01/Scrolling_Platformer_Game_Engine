package ooga.model.entities.maincharacter;

import ooga.model.entities.alive.ImmutableAliveBehavior;
import ooga.model.entities.entitymodels.BasicMainCharacter;

public class BasicMainCharacterBehavior implements MainCharacterBehavior{

  private ImmutableAliveBehavior aliveBehavior;
  private MainCharacterState mainCharacterState = MainCharacterState.RUNNING;
  private int score;
  private boolean hasPowerUp;

  /**
   *
   * @param aliveBehavior alive behavior
   */
  public BasicMainCharacterBehavior(ImmutableAliveBehavior aliveBehavior){
    this.aliveBehavior = aliveBehavior;
    score = 0;
    hasPowerUp = false;
  }

  /**
   * in case this has a power up
   * @param aliveBehavior alive behavior
   * @param hasPowerUp has power up
   */
  public BasicMainCharacterBehavior(ImmutableAliveBehavior aliveBehavior, boolean hasPowerUp){
    this.aliveBehavior = aliveBehavior;
    score = 0;
    this.hasPowerUp = hasPowerUp;
  }

  /**
   * in case this has a power up
   * @param basicMainCharacter main character from which old behavior is grabbed
   */
  public BasicMainCharacterBehavior(BasicMainCharacter basicMainCharacter){
    this.aliveBehavior = basicMainCharacter.getAliveBehavior();
    score = basicMainCharacter.getScore();
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

  /**
   * @return if has power up
   */
  @Override
  public boolean hasPowerUp(){
    return hasPowerUp;
  }

  /**
   * set has power up
   */
  @Override
  public void setHasPowerUp(boolean hasPowerUp){
    this.hasPowerUp = hasPowerUp;
  }


}
