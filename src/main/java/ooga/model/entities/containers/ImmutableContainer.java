package ooga.model.entities.containers;

import ooga.model.entities.ImmutableEntity;

/**
 * Control use of entity container in the front end (don't allow view to add entities)
 */
public interface ImmutableContainer {

  /**
   * Get ImmutableEntity at index
   * @param index index in container
   * @return ImmutableEntity
   */
  public ImmutableEntity getEntity(int index);



}
