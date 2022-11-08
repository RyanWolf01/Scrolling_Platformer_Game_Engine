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
Scenario 1
Scenario 2
Scenario 3
### Ryan
Scenario 1
Scenario 2
Scenario 3
