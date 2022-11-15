package ooga.model.collisions.collision_handling.v2;

import ooga.model.ImmutableInfo;
import ooga.model.Info;

public abstract class Collidable {
  public Collidable() {

  }

  public Info collide(Collidable other) {
    if (! other.getImmutableInfo().hasKey(ImmutableInfo.TYPE_KEY)) {
      throw new RuntimeException("Incorrect data for ImmutableInfo!");
    }

    String type = other.getImmutableInfo().get(ImmutableInfo.TYPE_KEY);
    if (type.equals("NEUTRAL")) {
      return onNeutralCollision(other);
    }
    else if (type.equals(this.getImmutableInfo().get(ImmutableInfo.TYPE_KEY))) {
      return onFriendlyCollision(other);
    }
    else {
      return onEnemyCollision(other);
    }

  }

  public abstract ImmutableInfo getImmutableInfo();
  protected abstract Info onNeutralCollision(Collidable other);
  protected abstract Info onEnemyCollision(Collidable other);
  protected abstract Info onFriendlyCollision(Collidable other);


}
