/**
 * Loads the CollisionChart object from a JSON file for entityType passed as a parameter.
 */
public interface CollisionChartLoader {
  CollisionChart loadCollisionChart(String entityType);
}