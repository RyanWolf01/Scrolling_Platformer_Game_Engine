package ooga.model.entities.maincharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.maincharacter.MainCharacterState;
import org.junit.jupiter.api.Test;

public class MainCharacterBehaviorTest {

  @Test
  void testUpdateScore1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.updateScore(1);
    assertEquals(1, mario.getScore());
  }

  @Test
  void testUpdateScore2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.updateScore(2);
    assertEquals(2, mario.getScore());
  }

  @Test
  void testUpdateScoreNegative() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.updateScore(-1);
    assertEquals(0, mario.getScore());
  }

  @Test
  void testSetState1() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.setMainCharacterState(MainCharacterState.USER_LOST);
    assertEquals(MainCharacterState.USER_LOST, mario.getMainCharacterState());
  }

  @Test
  void testSetState2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.setMainCharacterState(MainCharacterState.USER_WON);
    assertEquals(MainCharacterState.USER_WON, mario.getMainCharacterState());
  }

  @Test
  void testCheckLivesAndUpdateMainCharacterState() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeLives(-1);
    mario.checkLivesAndUpdateMainCharacterState();
    assertEquals(MainCharacterState.USER_LOST, mario.getMainCharacterState());
  }

  @Test
  void testCheckLivesAndUpdateMainCharacterState2() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.changeLives(1);
    mario.checkLivesAndUpdateMainCharacterState();
    assertEquals(MainCharacterState.RUNNING, mario.getMainCharacterState());
  }

  @Test
  void testSetPowerUp() {
    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);

    mario.setHasPowerUp(true);
    assertTrue(mario.hasPowerUp());
  }

}
