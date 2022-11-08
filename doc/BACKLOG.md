# Use Cases

### Michael Habib
* Handle Entity characteristics in backend
* Parsing data (JSON files) for Entity characteristics
* Allow Mario to have specific abilities (e.g. powerups)
* Allow Entity (specifically Mario for now) to be alive
* Move entities in backend
* Handle transfers of immutable types

### Andy Demma
* Wrapper class for all level data
* Display any platform, enemy, or player from Wrapper Level Data Class
* Scrollable levels with margins
* Wrapping Background
* Splash Screen where user can choose a game save
* In-Level save states which saves the current state of the level to a json file
* Between-Level save states which saves game information into json file

### Manith Luthria
* Organize entities in the Model
* Ensure no duplication of entities and hold all the entities that will be used in the game
* Tell View which entities to represent on the Stage
* Make gravity physics work
* Tell View what has changed since last frame
* Translate from Model coordinates to Camera coordinates
* Create subtypes for Entities


### Arman Shekarriz
* Ability to translate JSON files to UI and initial game screen
* Ability to handle main character movement button clicks and send information to model to update position
* Able to show custom errors to users from disallowed UI interactions
* Send collision event changes or movement event changes to UI to update the view with respect to positioning or changes in state of entities
* Handle user interactions on UI to change/save/reload/restart games
* Handle UI interactions to change languages or CSS styling of the game


### Ryan Wolfram
* Ability to detect whether a collision has occurred between two entities
* Functionality to tell each entity involved in a collision the characteristics of the collision
* Ability for each entity to do some action based on the data it’s given
* Implement API for physics calculations
* Create methodology for determining the hierarchy of entities
* Create powerups that can be utilized by characters

