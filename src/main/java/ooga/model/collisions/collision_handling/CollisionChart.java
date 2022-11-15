package ooga.model.collisions.collision_handling;

import ooga.model.actions.aliveactions.AliveAction;

/**
 * Represents the Chart corresponding with each Entity that determines how it should respond to
 * collisions.
 */
public interface CollisionChart {

  /**
   * Takes the CollisionData for a collision and outputs what action should be applied to the Entity
   * that owns this CollisionChart.
   *
   * @param collisionData the data representing the entities involved with the collision and the
   *                      collision physics
   * @return Action to be applied on the owner of this CollisionChart
   */
  AliveAction getAction(CollisionData collisionData);
}
