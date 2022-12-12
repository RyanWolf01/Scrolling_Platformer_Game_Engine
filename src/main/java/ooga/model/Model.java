package ooga.model;

import java.util.ArrayList;
import java.util.List;
import ooga.Main;
import ooga.model.actions.aliveactions.AliveAction;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.collisions.physics.CollisionPhysicsData;
import ooga.model.collisions.physics.CurrentCollisionContainer;
import ooga.model.collisions.physics.GravityEnforcer;
import ooga.model.collisions.physics.PhysicsCalculator;
import ooga.model.entities.entitymodels.ImmutableEntity;
import ooga.model.entities.entitymodels.CollidableEntity;
import ooga.model.entities.entitymodels.Entity;
import ooga.model.entities.containers.BackendContainer;

import java.util.ResourceBundle;
import ooga.model.entities.modelcallers.GameEnder;
import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;
import ooga.model.gamestatelisteners.GameStateListenerContainer;

/**
 * Backend logic is performed in here,
 * methods are called by the controller
 */
public class Model {
  private final GravityEnforcer gravityEnforcer;
  public static final ResourceBundle entityClassResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Entities");
  public static final ResourceBundle containerResources = ResourceBundle.getBundle(Main.PROPERTIES_PACKAGE+"Containers");

  private GameState gameState = GameState.RUNNING;
  BackendContainer entities;
  GameStateListenerContainer gameStateListenerContainer;

  public Model(BackendContainer entities, EndGameCallable endGameMethod){
    this.entities = entities;
    gravityEnforcer = new GravityEnforcer(entities);
    setEndGameMethods(endGameMethod);
  }

  /**
   * move all movers
   */
  public void moveMovers(){
    gravityEnforcer.applyGravityToAllMovers();
    entities.movers().moveAll(); // move all movers
  }

  public void handleMoveKey(MoverAction action){
    entities.mainCharacter().acceptMoveAction(action);
  }

  public void handleAliveKey(AliveAction action){
    entities.mainCharacter().acceptAliveAction(action);
  }

  public void checkForAndHandleCollisions() {
    PhysicsCalculator physicsCalculator = new PhysicsCalculator();

    preCollisionDetectionLoop();
    for (CollidableEntity collidable : entities.collidables()) {
      for (Entity entity : entities.entities()) {
        if (!collidable.equals(entity) && physicsCalculator.areColliding(collidable, entity)) {
          handleCollision(collidable, entity);
        }
      }
    }
  }

  public void checkAndHandleGameState() {
    if (entities.mainCharacter().getLives() <= 0) {
      gameState = GameState.USER_LOST;
    }
    else if (entities.mainCharacter().isEndPointHit()) {
      gameState = GameState.USER_WON;
    }
  }

  public GameState getGameState() {
    return gameState;
  }

  /**
   * Handles the collision with backend logic
   * @param colliderEntity first entity that collides
   * @param collidedEntity second entity that is collided with
   */
  // for breakpoint:
  // collider.getImmutableEntityInfo().get(ImmutableInfo.TYPE_KEY).equals("goomba") && collided.getImmutableEntityInfo().get(ImmutableInfo.TYPE_KEY).equals("mario")
  private void handleCollision(Entity colliderEntity, Entity collidedEntity) {
    if (!entities.isCollidable(colliderEntity)) return;
    CollidableEntity collider = getCollidableEntity(colliderEntity);

    if (collider.hasCurrentCollisionWith(collidedEntity)) {
      handleCollisionHelper(collider, collidedEntity, collider.physicsDataOfCurrentCollisionWith(collidedEntity));
    }
    else {
      handleCollisionHelper(collider, collidedEntity);
    }
    collider.getMyCurrentCollisions().get(collidedEntity).setCollisionIsFresh(true);
  }

  /**
   * To be called in the GameController before every time the Collision detection loop is
   * executed
   */
  private void preCollisionDetectionLoop() {
    removeNonFreshEntities();
    for (CollidableEntity collidable : entities.collidables()) {
      CurrentCollisionContainer currCollisions = collidable.getMyCurrentCollisions();
      for (ImmutableEntity otherEntity : collidable.getMyCurrentCollisions()) {
        currCollisions.get(otherEntity).setCollisionIsFresh(false);
      }
    }
  }

  private void removeNonFreshEntities() {
    for (CollidableEntity collidable : entities.collidables()) {
      CurrentCollisionContainer currCollisions = collidable.getMyCurrentCollisions();

      // from https://www.geeksforgeeks.org/convert-an-iterator-to-a-list-in-java/
      List<ImmutableEntity> keys = new ArrayList<>();
      currCollisions.iterator().forEachRemaining(keys::add);

      for (ImmutableEntity otherEntity : keys) {
        if (! currCollisions.get(otherEntity).collisionIsFresh()) {
          currCollisions.remove(otherEntity);
        }
      }
    }
  }

  private CollidableEntity getCollidableEntity(Entity collider) {
    for(CollidableEntity collidable : entities.collidables()){
      if(collidable.equals(collider)){
        return collidable;
      }
    }

    throw new RuntimeException("Improper usage of this private method. This should only be called"
        + "if it is known that collider is a CollidableEntity");
  }

  private void handleCollisionHelper(CollidableEntity collider, Entity collided) {
    collider.onCollision(collided, new PhysicsCalculator().calculatePhysicsData(collider, collided));
  }

  private void handleCollisionHelper(CollidableEntity collider, Entity collided, CollisionPhysicsData currCollisionPhysicsData) {
    collider.onCollision(collided, new PhysicsCalculator().updatePhysicsDataOfCurrentCollision(currCollisionPhysicsData));
  }

  private void setEndGameMethods(EndGameCallable endGameMethod) {
    entities.mainCharacter().setEndGameCallable(endGameMethod);
    entities.gameEnders().forEach((GameEnder gameEnder) -> gameEnder.setEndGameCallable(endGameMethod));
  }
}
