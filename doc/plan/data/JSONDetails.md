# How to set up level

Every game requires a certain amount of JSON files to set up
the game. There are a total of n files required.

1. Level.json
    * The level file describes how to set up the entities in the world
    for the level. The number of entities never changes, but not all are displayed 
    at all times. The level.json file contains details about every entity and its starting
    coordinates. The main subtypes of entities are: enemies, main character, structures, powerups (add life, disco star, etc).
    Furthermore, the file author has to define the size (length and width) of every 
    entity and its collision types.