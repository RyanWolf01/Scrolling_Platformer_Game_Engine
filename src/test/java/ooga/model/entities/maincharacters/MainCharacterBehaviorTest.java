package ooga.model.entities.maincharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ooga.model.entities.entitymodels.BasicMainCharacter;
import ooga.model.entities.info.EntityInfo;
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

//  @Test
//  void testUpdateScoreNegative() {
//    BasicMainCharacter mario = new BasicMainCharacter(null,0, 0, 2, 2, new EntityInfo("MARIO"), null);
//
//    mario.
//    assertEquals(0, mario.getScore());
//  }

}
