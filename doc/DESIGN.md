# OOGA Design Final
### Andy Demma, Manith Luthria, Arman Shekarriz, Ryan Wolfram, Michael Habib

# OOGA
### 9
### Andy Demma (abd35@duke.edu), Michael Habib (mwh35@duke.edu), Manith Luthria (mnl21@duke.edu), Arman Shekarriz (abs78@duke.edu), Eric Tishler (ert26@duke.edu), Ryan Wolfram (rpw17@duke.edu)



## Project Roles

* Andy Demma

  In this project I worked solely on the frontend. I handled most of the code that took the entities created by the backend and displayed them on the screen, updating their x and y to make the level scrollable. Also, I worked on any other front-end screen, which displayed anything from menus to a high score list. Lastly, I worked on the methods where the view would call the controller, initiating a new frame, then getting the frame and applying its changes to the screen.

* Manith Luthria

  In this project I worked on the connection between the frontend and the backend, and I handled most of the code in the controller. I regularly met with Michael and Ryan to discuss backend features and how to best implement them. I also connected the JSON parsing API to other classes that needed to make use of it. Finally, I worked on the large extension to the project, the online database which saves high scores

* Arman Shekarriz

  In this project I worked on handling JSON interactions in the game in a JSON Parsing API and worked on determining different important pieces of information about initial game state from the controls, collisions, and level JSON files fed into a program by the user. In addition, I worked on recreating a level at a specific point in the game in a JSON format and implemented saving and loading games from the pause screen that allows users to save a game at any point, giving it a name of their choice, and reload any of the saved games at any point in the game as well.

* Ryan Wolfram
  In this project, I worked on implementing the collision handling code that allows actions to be applied to entities from a JSON file. I worked on determining when collisions occur between entities, and detecting the direction in which these collisions occur. I worked with Mike to create actions and modify the entities to make it so that actions can be effectively applied. I also created the CollidableEntity and worked on the implementation of some of the Entity classes.

* Michael Habib
  In this project, I worked primarily in the backend, specifically on the different entity models as well as action classes specific types of entities can take. This included the MovementI, Alive, and MainCharacter API’s as well as the concrete classes that went with these interfaces and abstract classes. I also helped on the physics side of things, making the classes that enforce gravity on the moveable entities. I regularly worked with Manith and Ryan on the backend in general and collision-handling respectively.

## Design Goals

There are many aspects of the project that are easy to add on to, and would have been done if there were more time

* It is very easy to add a new game. All one has to do is write the JSONs and choose the powerups and collisions. Even the database for high scores works if you edit it slightly. Before the code freeze, we only had one game working, but now we have 3

* Adding new powerups is very easy. One just needs to write new actions to affect the main character and then put the new powerup into the JSON

* Adding new GUI elements is simple. The View’s design is very clean and it is simple to add a new button and/or status display


## Basic Design

When the game opens up, the user is taken to a start screen where they select their language and what kind of game they would like to play. When the user clicks the “Go” button, the View initializes the controller, which initializes the Model. The controller then calls the JSONInfoDecoder to decipher the chosen JSON files. Using the data structures created by the JSONInformationDecoder, the controller creates the Model and all the containers for the entities that will be required for the game.

The game updates itself at an arbitrary rate set in the View in the scene animation. At every step, the controller.step() method is called. At this point, the controller checks for collisions and sends information from key presses to the backend to be handled. After the model completes the collision handling and entity movement, the controller updates the frontend Node container to reflect the changes in the backend. The View then takes this Node Container, Removes any old entities that are not in this frame’s nodes, and adds any new nodes

The Model works through a hierarchy of active entity model classes, at the top of which is the Entity abstract class. Then, there’s a StaticCharacter, which doesn’t move but has lives, a MovingEntity, which moves but doesn’t have lives, a MovingCharacter that has both lives and movement, and a MainCharacter, that has lives, movement, is user controllable, and contains a few other distinct MainCharacter behaviors. These Movement, Alive, and MainCharacter behaviors are all separate classes that can be changed on the fly: for instance, with a new power up, the way the lives, movement, scores, etc. are calculated can all be changed without creating a new instance of the basic entity model. These are all their own API’s, in that there are interfaces and abstract classes that describe these behaviors as well as corresponding Action classes that each execute a specified action on a given entity of its type.

In order to handle collisions between two entities, we created a CollidableEntity class. This class contains a method called onCollision(Entity entity, CollisionPhysicsData) which is an API method to execute the relevant actions on the CollidableEntity based on what it's colliding with and the nature of the collision. CollisionPhysicsData contains information about which direction the collision occurred from form the perspective of the CollidableEntity. When onCollision is called, a CollisionData object is created, which encapsulates all the relevant information regarding the collision in a hashmap. This CollisionData object is passed to the CollidableEntity’s CollisionChart, which is an object that contains data about how to handle certain collisions. The CollisionChart loops through the Criteria checked against for a match (a match, and if there’s a match between its Criteria and the CollisionData, an ActionDataContainer class is returned, which contains a list of ActionData objects. Each ActionData object contains Strings that allows the CollidableEntity to use reflection to generate necessary Action objects using different ActionParsers for different types of Actions being created.These Actions are then applied to the child classes that extend CollidableEntity.

## Assumptions or decisions made to simplify the design

Users cannot create their own actions, collision types, or new controls without adding the related functionality to the code. The type of collision and collisions event, the type of entity created, and the game controls mapped to specific keys all need to be recognizable by the game program, or in other words capable of being handled in the java code. While adding the functionality to handle new actions, collisions, or controls is simple to implement in the code based on our design, and we feel we have accounted for all of the important controls, collisions, and actions, users will not be able to create new types of these purely in JSONs. We are also assuming the games cannot be multiplayers (there cannot be multiple Mario's, DoodleJumpers, etc.) and that we will never run two games at the same time (This is because the games are real time and need the keyboard to run. You cannot use a keyboard on two windows at once. The features affected by these assumptions is that we are not creating a list of heroes, and not accounting for reading in different files to start multiple games.



## Difference between original plan and final product

* In the original plan we decided to have a very complex controller package, but in the final product it was simplified to just have one GameController with a substantial API that connects every aspect of the game.

* We separated actions into AliveActions and MoverActions because as we developed the entities class hierarchy, we realized we had to separate the Movers and Livers into separate interfaces. This deviates from our original plan for the actions and forced us to write more code than we originally would have. In a similar vein, we separated the backend model entity classes into these separate behaviors and extracted the behaviors themselves into separate classes. This allowed for more flexibility in changing the movement and alive behaviors of the entities on the fly as the program was running instead of the behaviors being hardcoded into the model classes themselves.


## Describe in detail how to add new features to the project

One feature to be added would be additional cheat keys to the game. In order to add more of these, we would need to add the new controls (in whatever string format desired) as new classes in the program. For example, if we wanted a new control JumpBackward (where the character jumps up and moves backward at the same time) we would have the backend functionality to handle this, but would need to add a new class called JumpBackward that handles this control. Features such as adding new types of characters, or adding new types of recognized collision events work similarly: in order for the new strings in the JSON to be recognized, a new character class would have to be made. Again, the framework to handle these new characters and collisions is implemented, so adding them would be simple, but would entail modifying the code itself. While this setup is slightly less flexible in that new controls or character types cannot be made directly within JSONs, we still believe it is well designed as the amount of additional modification to the code is limited, and restricted to new classes, so that none of the existing functionality in the code is modified, making new features or additions an extension of the current code. This means that other users or developers aiming to add functionality for new types of characters or controls to make games more flexible would not be changing or altering the foundation of the code, but adding new classes from this, which is desirable.
