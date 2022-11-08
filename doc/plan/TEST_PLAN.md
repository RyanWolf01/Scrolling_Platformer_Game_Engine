1. One testing strategy we can use is to make mock classes for parameters that we need to pass 
to the API. Then we can provide those mock classes with fixed values that can make testing 
significantly easier.
2. We can also make custom errors in the program to check the functionality or error checking in a 
more robust way.
3. Another testing strategy we can use is to ensure that all the APIs used in the view are distinct and disconnected from everything
but the controller where needed, and additional updating of JSON files and other properties is limited, to make testing the view as simple as possible.
4. Another testing strategy we can use related to the previous is ensuring external dependencies (perhaps in the context of the extension or databases
we aim to implement) are limited in where they are used in the project and remain within the controller, and thus do not affect model and view
testing. This may also include making mock classes if necessary to pass to the API to test.

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
* Test to ensure that the model location is updated after the user selects to move the main character on the screen, this can be done by
by testing the method updateCharacterLocation in GameController to ensure it works as expected, this can be a happy test case by setting the proper
updated location following a user change and checking they are equal or a sad test path if we give the method the wrong things to work with or call it incorrectly with the incorrect
parameters or expecting the wrong return.
* Testing for the JSON file, and ensuring that the EntityList is made correctly from a sample JSON input, this can be done by testing the method in 
JSONTranslator and creating sad test paths by giving it the incorrect path or an unavailable or incorrect file type, and happy test paths by giving it real
acceptable (simple) examples and checking the output against the expected EntityList output from the method, we can also test that the correct
errors are outputted/given in the case of an unavailable or wrong file path.
* Testing to ensure that the LevelDataWrapper information that stores all the information in the game is updated properly on collision events and the method to send collision updates to remove
elements on the frontend in the controller functions as expected. All of this is stored in the controller, and thus can be tested by mocking potential starting information for the game and the setup
and checking what happens to the game info when a collision is forced to occur, and observing the outputted affect it has on the UI, and whether the element disappears. Using simple example,
we can check to ensure that when a collision occurs, the sendCollisionUpdateToView method works as expected and tells the view to alter what is it rendering on the screen and changes what is
held in the LevelDataWrapper.

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
