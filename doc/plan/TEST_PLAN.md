1. One testing strategy we can use is to make mock classes for parameters that we need to pass 
to the API. Then we can provide those mock classes with fixed values that can make testing 
significantly easier.

### Michael
* Test if ImmutableEntity actually restricts the data access of the EntityList from the view. For example,
test the methods available to the Entity returned by EntityList using reflection.
* Test the functionality of the living API on entities. For example, see if calling kill() on an Entity
that implements Alive actually causes the lives counter to go to 0.
* Test the functionality of the LifeIncrementer. For example, see if an entity that implements
  LifeIncrementer can increase the lives of the entity. 
### Andy
* Test if the Camera actually moves when the player gets outside the movable box. I will test the camera location after a movement.
* Test if an enemy actually disappears once its model counterpart has died.
* Test how the camera reacts if Mario is suddenly moved (teleported) to outside the playable area. What will the camera do at this moment?
### Manith
* Try to add duplicate entries to EntityContainer
* Make sure character Y_coordinate drops when not on platform
* Try to enter out of bounds coordinates and make sure an error is thrown
### Arman
* Test
* 
Scenario 3

### Ryan
Scenario 1
* Test: CollisionInformation detectCollision(Entity entityA, Entity entityB)
Set the position of entityA and entityB to intersect each other such that they're colliding on
each other's side. Call detectCollision(entityA, entityB) and check the collisionOccured property
of the CollisionInformation object that's returned to make sure it's true. Also check to see
if the CollisionInformation returns the string "SIDE".

Scenario 2
* Test: CollisionChart loadCollisionChart(String entityType)
Load in a JSON object dynamically with hardcoded values. Then send the correct entityType string
to the method. Step through all the values inside CollisionChart (which encapsulates a JSON
object) and make sure that they match with the JSON object originally loaded.

Scenario 3
* Test: void handleCollision(CollisionInformation collisionInformation, Entity entityA, Entity entityB)
Test to make sure that the state of entityA changes how it's supposed to after colliding with
entityB, given that you know the rules for how entityA is supposed to react to collisions. For
example, make sure that entityA moves by 10 pixels if an EntityCommand to move entityA by 10 
pixels is executed on it. Also write a test such that no match occurs/no criteria exist with
entityA to handle some sort of collision, and ensure that a NoCriteriaMatched exception is thrown.
