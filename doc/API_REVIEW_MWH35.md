# Chosen API: Movement (Mover interface, AutomaticMover interface, MoverAction interface, MovingEntity, MovingCharacter, etc.)

# Part 1
* How does your API encapsulate your implementation decisions?
    * The Movement API encapsulates all the implementation of moving any entity, whether the entity
  is user-controlled or automatically-controlled. By automatic movement, I refer to Goomba's, turtles,
  moving platforms, etc. Any capability for movement is thus completely encapsulated in this API, and thus
  this API is essentially closed. 
* What abstractions is your API built on?
  * From the top level, the API is built on a Mover interface, which has a few crucial methods: move(), which
  changes the x and/or y positions of the mover; changeVelocities(), which changes the x and/or y velocities of the mover;
  resetVelocities(), which resets the x and/or y velocities of the mover; and a getter for each of the two velocities, as these
  getters are needed in order to implement move(). For AutomaticMovers, there is an AutomaticMover interface that extends the
  Mover interface, adding only the automaticMove() method, which forces the subclass to implement some way of automatically moving the entity.
  Apart from these interfaces, there is a MoverAction interface that has one method, execute(), which moves the passed in 
  Mover. Of course, there are then abstract classes for MovingCharacters, MovingEntities, etc.
* What about your API's design is intended to be flexible?
  * The MoverAction is very flexible as it allows for any type of movement to be implemented as its own class, so there is no restriction
  on what type of movements any particular Mover is capable of. 
* What exceptions (error cases) might occur in your API and how are they addressed?
  * Exceptions include if the Mover tries to go off the screen, and right now only the case of Mario falling off
  the screen is handled: in this case, Mario is killed (i.e. he loses a life). I can't think of any other exceptions right now, but I'll
  keep thinking.
# Part 2
* How do you justify that your API is easy to learn?
  * The person to whom I was explaining the API seemed to understand it almost completely. It is seems fairly standard 
  to have a Mover interface with a basic move() method and various MoverActions that can be initialized as their own classes and 
  then used appropriately depending on the scenario. However, it may be hard to understand the need for an AutomaticMover interface, instead
  of just implemented the move() method differently for automatic movers. This is necessary, though, as the AutomaticMovers need
  to use MoverActions instead of user-controlled inputs.
* How do you justify that API leads to readable code?
  * The interface and method names are all very readable, such as move() and changeVelocities(), and there are javadoc
  comments for everything.
* How do you justify that API is hard to misuse?
  * Again, the code is readable; the names, in particular, are all carefully chosen to not be misinterpreted.
* Why do you think your API design is good (also define what your measure of good is)?
  * The design of the broad Mover interface alongside MoverActions makes all the Movers more active; each concrete class is
  more active on a lower level as opposed to having a higher-level game class that controls movements, etc. Additionally,
  everything is essentially closed at this point, but it is easy to make new types of movements and movers with new
  classes.


