package ooga.model.collisions.physics;


import static org.junit.jupiter.api.Assertions.assertTrue;
import ooga.model.entities.info.EntityInfo;
import ooga.model.entities.livingentities.movingentities.maincharacters.Mario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GravityCalculatorTest {

  private Mario mario;

  @BeforeEach
  public void setup(){
    EntityInfo info = new EntityInfo("mario");
    mario = new Mario(null, 10, 10, 2, 2, info);
  }

  @Test
  void testEnforceGravityPos2(){
    GravityCalculator gravityCalculator = new GravityCalculator();
    assertTrue(gravityCalculator.checkInAir(mario));
  }

  // TODO: need more tests but involves creating collisions

}
