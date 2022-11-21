package ooga.model.collision_handling;

import static ooga.model.collisions.collision_handling.CollisionData.COLLISION_PREFIX;
import static ooga.model.collisions.collision_handling.CollisionData.MY_PREFIX;
import static ooga.model.collisions.collision_handling.CollisionData.OPPONENT_PREFIX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.model.entities.data.Info;
import ooga.model.collisions.physics.CollisionPhysicsInfo;
import ooga.model.collisions.collision_handling.CollisionData;
import ooga.model.collisions.physics.CollisionDirection;
import ooga.model.entities.data.EntityInfo;
import org.junit.jupiter.api.Test;

public class CollisionDataTest {

  @Test
  public void testCollisionData() {
    EntityInfo entityAInfo = new EntityInfo("GOOMBA");
    EntityInfo entityBInfo = new EntityInfo("MARIO");
    CollisionPhysicsInfo cpi = new CollisionPhysicsInfo(true, CollisionDirection.LEFT);

    String[] kvPairsA = new String[]{"powerUp", "NONE", "test", "FOO"};
    String[] kvPairsB = new String[]{"powerUp", "STAR", "BTest", "BAR"};
    String[] cpiKvPairs = new String[]{"substance", "WATER"};

    setInfo(entityAInfo, kvPairsA);
    setInfo(entityBInfo, kvPairsB);
    setInfo(cpi, cpiKvPairs);

    CollisionData collisionData = new CollisionData(entityAInfo, entityBInfo, cpi);

    String CP = COLLISION_PREFIX;
    String MP = MY_PREFIX;
    String OP = OPPONENT_PREFIX;
    String[] combined = new String[]{CP + "DIRECTION", "LEFT", MP + "powerUp", "NONE", MP + "test", "FOO", OP + "powerUp", "STAR", OP + "BTest", "BAR", CP + "substance", "WATER"};

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
