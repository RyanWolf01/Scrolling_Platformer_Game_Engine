ooga
====

This project implements a player for multiple related games.

Names: Andy Demma, Manith Luthria, Arman Shekarriz, Ryan Wolfram, Michael Habib


### Timeline

Start Date: 11/02/22

Finish Date: 12/15/22

Hours Spent: 1,752,000,000

### Primary Roles

* Andy Demma -> Frontend
* Manith Luthria ->Backend, Controller, Database, Frontend
* Arman Shekarriz ->Controller, Data Parsing
* Ryan Wolfram ->Backend
* Michael Habib ->Backend

### Resources Used
www.piskelapp.com - Used to create Sprites for the game


### Running the Program

Main class: Main

Data files needed:
* resources/backgrounds - The background image of a level
* resources/buttonicons - Icons for the pause menu
* resources/games - The images for any entity
* resources/icongames - The Icon for the startmenu when a game is chosen
* resources/properties - All of the strings for multiple language functionality, reflection, and exceptions
* resources/savedgames - The location where games are saved
* data/games/(mario or dash or doodle) - location where levelfolders are saved (levelfolder name is custom per level)
* /levelfolder/level.json - Describes all entities in a level
* /levelfolder/collisions.json - Describes the keys for controlling a character
* /levelfolder/controls.json - Describes how entities interact with other entities



Features implemented:
* Choose Language in Start Screen
* Choose Level in Start Screen
* View High Scores in End Screen
* Camera is Scrollable in Level
* Display Entities from Node Container in Level
* Grab High Scores from Database to place in End Screen
* Show exceptions as Alerts in Front End
* Have Pause Screen with functional Buttons
* Save a Game at any point
* Load Said Game from the pause screen or start screen
* Return back to start menu from pause screen
* Design any level with level.json
* Decode the json files for a level to be read by the backend
* Have automatically moving enemies
* basic movement, including gravity
* powerups
* coins (score incrementers)
* living entities


### Notes/Assumptions

Assumptions or Simplifications:

* Users cannot create their own actions, collision types, or new controls without adding the related functionality to the code. The type of collision and collisions event, the type of entity created, and the game controls mapped to specific keys all need to be recognizable by the game program, or in other words capable of being handled in the java code. While adding the functionality to handle new actions, collisions, or controls is simple to implement in the code based on our design, and we feel we have accounted for all of the important controls, collisions, and actions, users will not be able to create new types of these purely in JSONs. We are also assuming the games cannot be multiplayers (there cannot be multiple Mario's, DoodleJumpers, etc.) and that we will never run two games at the same time (This is because the games are real time and need the keyboard to run. You cannot use a keyboard on two windows at once. The features affected by these assumptions is that we are not creating a list of heroes, and not accounting for reading in different files to start multiple games.


Interesting data files:

* The collisions.json file is very interesting. One can define the collision actions for every direction for any combination of entities. One can also set the parent entity for an entity so that it may inherit the collision properties of the parent.

Known Bugs:

* Main character sinks into the platform slightly, cannot walk straight from one platform to another level one, will collide with side of next platform

Challenge Features:

* Loading files: From the pause screen, the user can load a new game from a different directory.

* Saving files: The user can save their progress by going to the pause screen and choosing where to keep their savefile. This will record the current position, size, and remaining lives of every entity in the level.

* Online database: High scores are saved to and read from an online Firebase realtime database.

* Collision-handling in the backend: this was hard to do from scratch, and it’s still not perfect
* Gravity
* Scrollable Camera: It took a lot of sketching and relative math to define how a camera would move the entities when the main character would move



### Impressions

We enjoyed working on this project overall, which was both a result of our love for Mario as well as our team chemistry. It was fun to see the final project evolve, and everyone was easy to work with which made it a great experience.

