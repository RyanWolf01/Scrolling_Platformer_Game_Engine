# Use Cases

1. Camera - View
 - The main part of the view will be a camera of a certain size. This will display objects that are stored in the Model and re-render every timeframe (eg. 1/60 s).

2. Entity - Model
 - Every part of the game will extend the Entity class. It will have an initial X coordinate, and initial Y coordinate, current X, current Y. There will be subtypes of entities (interactive, non-interactive, moving, static) which will have their own properties.

3. Moving Entity - Model
 - There should be an X_velocity and Y_velocity variable for each moving thing. Every step() will increment the x and y of the entity by the velocity. This will work with the collision class.

4. Collision - Model
 - There will be various types of collisions for various 