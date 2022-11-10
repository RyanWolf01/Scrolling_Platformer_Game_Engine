package ooga.model.collisions.collision_handling;

/**
 * When this is first initialized, we can read in every single collision chart, then store it
 * in a map, with the collision chart's path as a key. That way, whenever someone calls
 * getCollisionChart, we don't need to read in the files again. We can just get it from the hashmap
 * (where the path to the collision chart is a key in the hashmap).
 */
public class CollisionChartReader {

  public CollisionChart getCollisionChart(String collisionChartPath) {
    return null;
  }
}
