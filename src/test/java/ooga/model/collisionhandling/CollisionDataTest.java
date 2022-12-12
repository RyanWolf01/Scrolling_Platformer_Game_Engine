package ooga.model.collisionhandling;

import static ooga.model.collisions.collisionhandling.CollisionData.COLLISION_PREFIX;
import static ooga.model.collisions.collisionhandling.CollisionData.MY_PREFIX;
import static ooga.model.collisions.collisionhandling.CollisionData.OPPONENT_PREFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.entities.info.Info;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.collisions.collisionhandling.CollisionData;
import ooga.model.collisions.physics.CollisionDirection;
import ooga.model.entities.info.EntityInfo;
import org.junit.jupiter.api.Test;

public class CollisionDataTest {

  @Test
  public void testCollisionData() {
    EntityInfo entityAInfo = new EntityInfo("GOOMBA");
    EntityInfo entityBInfo = new EntityInfo("MARIO");
    CollisionPhysicsData cpi = new CollisionPhysicsData(true, 1, CollisionDirection.LEFT);

    String[] kvPairsA = new String[]{"powerUp", "NONE", "test", "FOO"};
    String[] kvPairsB = new String[]{"powerUp", "STAR", "BTest", "BAR"};

    setInfo(entityAInfo, kvPairsA);
    setInfo(entityBInfo, kvPairsB);

    CollisionData collisionData = new CollisionData(entityAInfo, entityBInfo, cpi);

    String CP = COLLISION_PREFIX;
    String MP = MY_PREFIX;
    String OP = OPPONENT_PREFIX;
    String[] combined = new String[]{CP + "DIRECTION", "LEFT", MP + "powerUp", "NONE", MP + "test", "FOO", OP + "powerUp", "STAR", OP + "BTest", "BAR"};

    for (int i = 0; i < combined.length; i += 2) {
      assertTrue(collisionData.hasKey(combined[i]));
      assertEquals(collisionData.get(combined[i]), combined[i + 1]);
    }
  }

  private void setInfo(Info info, String[] data) {
    for (int i = 0; i < data.length; i += 2) {
      info.set(data[i], data[i+1]);
    }
  }

}
