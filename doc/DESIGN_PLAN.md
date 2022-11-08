# OOGA Design Final
### Andy Demma, Manith Luthria, Arman Shekarriz, Ryan Wolfram, Michael Habib

# OOGA Plan Discussion
### 9
### Andy Demma (abd35@duke.edu), Michael Habib (mwh35@duke.edu), Manith Luthria (mnl21@duke.edu), Arman Shekarriz (abs78@duke.edu), Eric Tishler (ert26@duke.edu), Ryan Wolfram (rpw17@duke.edu)



## Project Goals

* Andy Demma

* Manith Luthria
  I would like to learn more about encapsulation and wrapping data structures to promote cleaner code writing. I am learning to plan more before I begin to write code. I also hope to learn to use active methods more then getter/setter methods.

* Arman Shekarriz
  I would like to learn more about how to best use reflection and other techniques to promote encapsulation and allow the model to the isolated from the view to 
obey MVC. I also would like to learn more about how to test code in the controller area most effectively and become better at mocking examples to achieve better, 
more robust testing that allows for more confidence in the code written. I also would like to work across all areas of the project and continue to try and become better
and the design nuances that accompany many of the smaller tasks in projects that can have a major impact as a whole.

* Ryan Wolfram
  I would like to use the good design principles that we’ve learned in this course. Namely, I want 
to make sure that our code completely follows the open-close principle in regard to adding new 
game entities and even types of games. I also want to be an active contributer during team
meetings and uphold high standards for good design of our code (which ultimately makes our lives
easier down the road).

* Michael Habib
  *In terms of teamwork, I hope to be more understanding of and open to other teammates’ ideas. On the technical side, I hope to plan data protection and control using encapsulation, interfaces, and records early on. Additionally, I hope to use more abstraction and have more complex hierarchies, making lower-level classes more active.


## Project Genres

* Possible genres
* Scrolling Platform
* Strategy Based
* Grid Based (tetris, etc.)
* Chosen genre
  *Scrolling platform games such as Super Mario Bros, Doodle Jump, Sonic, Geometry Dash

## Project Extensions

* Possible extensions
  *Online data storage (for leaderboards, save files, etc.), have user change design preferences, Race against past version (artificial player), load games, save games

* Chosen extensions
  *Online data storage (complex), preferences (basic), load and save game (basic) artificial player (basic)

## Project Effort Emphasis

Emphasis will be on mostly getting positive


## Team Name

* Possible names
    * Doom Scrollers, Mighty Men, Super Bros

* Chosen name
    * Doom Scrollers


## Project Roles

* Michael Habib
  *Backend -> entity design, data encapsulation and protection, movement classes

* Arman Shekarriz
  *Controller work and generalist -> doing JSON file handling, translation between front and backend that sends collision information to frontend to remove or change visual status of entities and vice verse for UI interactions, properties things with languages and

* Andy Demma
    * Frontend ->


* Manith Luthria
    * Backend, Control -> Work on collision and entity physics, converting Model entities to View JavaFX objects


* Ryan Wolfram
  * Backend -> Work on collision handling rules between entities. Work on the complex extension of writing game data to a database


## UML Diagram Link
* https://lucid.app/lucidchart/30198d88-bff7-43fa-8147-71c6e324ba87/edit?viewport_loc=-2440%2C-61%2C2514%2C1581%2C0_0&invitationId=inv_a7780354-b652-4cce-90f0-edd1ef2f3944

## Design goals

#### What Features are Easy to Add
* New types of characters and enemies. New variations of enemies can be added via a JSON file without 
adding any new classes or code.
* New abilities for characters. Easy to add subclasses.
* New types of movements that extend movement interface.
* New mediums to move through (change gravity calculation)

## High-level Design

#### Core Classes
* Entity class -> models any object in backend, such as Mario, Platform, etc.
* Movement interface -> allows objects to move
* CollisionHandler interface -> determines how to handle collisions
* Alive interface -> determines behavior if Entity is alive
* StepController -> handles moving objects

## Assumptions that Affect the Design
* Assuming the games cannot be multiplayer (there cannot be multiple Mario's, DoodleJumpers, etc.)
* Assuming we will never run two games at the same time
#### Features Affected by Assumptions
* We are not creating a list of heroes (i.e. Marios) 
* We are not accounting for reading in different files, etc. to start multiple games

## Significant differences from Original Plan


## New Features HowTo

#### Easy to Add Features

#### Other Features not yet Done

