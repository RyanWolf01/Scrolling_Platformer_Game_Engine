package ooga.model.collisions.collision_handling;

import ooga.model.actions.Action;
import ooga.model.collisions.CollisionData;
import ooga.model.entities.ImmutableEntityInfo;

public class PostCollisionActionGetter {
  private CollisionChartReader myCollisionChartReader;
  
  public PostCollisionActionGetter() {
    myCollisionChartReader = new CollisionChartReader();
  }

  public Action getPostCollisionAction(ImmutableEntityInfo targetEntityInfo, ImmutableEntityInfo sourceEntityInfo, CollisionData collisionData) {
    if (! targetEntityInfo.hasKey(ImmutableEntityInfo.COLLISION_CHART_KEY)) {
      throw new RuntimeException("Target Entity doesn't have a collision chart!");
    }

    CollisionChart collisionChart = myCollisionChartReader.getCollisionChart(targetEntityInfo.get(ImmutableEntityInfo.COLLISION_CHART_KEY));
    
    return null;
  }
}
