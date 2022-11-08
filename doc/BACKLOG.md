# Use Cases

1. Camera - View
 - The main part of the view will be a camera of a certain size. This will display objects that are stored in the Model and re-render every timeframe (eg. 1/60 s).

2. Entity - Model
 - Every part of the game will extend the Entity class. It will have an initial X coordinate, and initial Y coordinate, current X, current Y. There will be subtypes of entities (interactive, non-interactive, moving, static) which will have their own properties.

3. Moving Entity - Model
 - There should be an X_velocity and Y_velocity variable for each moving thing. Every step() will increment the x and y of the entity by the velocity. This will work with the collision class.

4. Static Entity - Model
 - These entities will not be able to move but when collided with they will affect the things that collide with it.

5. Collision - Model
 - There will be various types of collisions for various hits. Every entity will dictate what happens when it is hit on each side (left, right, top, bottom). The type of collisions could be as follows: STOP, BOUNCE, KILL, ADD_LIFE, etc. These would affect the collided objects by changing their X_velocity, Y_velocity, number of lives, etc.

6. Character Movement - Model
 - The arrow keys will increment and decrement the x and y velocity up to an upper limit that is defined in the program. There will be a function to determine if the character is onTheGround() and if not the y_velocity will decrement by a constant. When the character is on the floor the top_collision component of the floor will tell the character to set the y_velocity to 0.


7. 