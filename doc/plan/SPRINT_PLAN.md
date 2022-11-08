### Sprint 1
## Andy
- Wrapper Class for Level Data
- Display all members of Wrapper Class in View
- Simple Splash Screen to Start/Choose Game
## Michael
- Handle Entity characteristics in backend
- Parsing data (JSON files) for Entity characteristics
- Allow Entity (specifically Mario for now) to be alive
- Move entities in backend
- Handle transfers of immutable types
## Arman
- JSONTranslator API
- Translating JSON file to make entityContainer, passing information to view and model to build initial layout and get initial positioning for everything in the model
- To this end implementing JSON interpreter, and parsing JSON files without making parser classes
- GameController API
- Ability to handle main character movement button clicks and send information to model to update position, methods in
## Manith
- Get Model information to View using the controller
- Implement gravity physics
- Make simple collision setup for structures
## Ryan
- Implement the rules for handling collisions with file
- Create the classification for how to identify types of objects
- Work on the data structure that stores all the entities
- Work on the structure of APIs in the model for entities


Sprint 2
Use Case #x - Group Member
Andy
Scrollable levels with margins
Wrapping Background
Between-level saving and loading into a json file
Manith
Translate starting code to all games
Define collision for enemy types
Create save/load files
Arman
GameController
Send collision event changes or movement event changes through GameController to UI to reflect changes on the frontend
Handle user interactions on UI to change/save/reload/restart games, coordination with Andy
Miscellaneous/properties info
Handle UI interactions to change languages or CSS styling of the game
Adding properties files to support all of this, languages and whatnot
Ensuring reflection used in controller effectively
Mike
Allow other entities to be alive, depending on type (e.g. enemy, etc.)
Allow other entities to move, also depending on type
Account for different mediums (e.g.. air, water, etc.)
Ryan
Work on defining collisions for enemy types
Work on implementing super powers and the physics for those powerups
Work on implementing physics for water games

Complete
Andy
Choose a game save in Splash Screen and display simple data from chosen file
In-Level save states which saves the current state of the level to a json file
Arman
Custom errors and ensuring proper errors returned to user on UI misuses
Testing and refactoring
Ryan
Save the high scores and level data to a database
