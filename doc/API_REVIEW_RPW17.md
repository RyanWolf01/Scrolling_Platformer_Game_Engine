jjh85

Summary: Ultimately my partner thought that our implementation made sense, although he thought that
it shouldn't be as reliant on JSON for implementing the logic of collision handling and perhaps 
should integrate some of that into java code instead.

Part 1
How does your API encapsulate your implementation decisions?
* Our API uses interfaces, allowing us to call the interfaces and get the same result regardless 
  of the implementation underneath.

What abstractions is your API built on?
* It's built on abstracting out collision handling. Essentially, we feed the API data on two things
  that have collided and receive information in return that allows us to obtain an action to apply
  on the thing that originally collided.

What about your API's design is intended to be flexible?
* Our API is inteaded to be flexible in regards to preloading collision charts versus reading them
from json and/or doing reflection during runtime. Currently, we read in all our data necessary
for collision handling from a JSON file at the beginning of the program, but the program would
also work if that data was read in repeatedly during each collision (which doesn't make sense
from an efficiency perspective but might have been easier / quicker to create during early 
implementations).

What exceptions (error cases) might occur in your API and how are they addressed?
* One error case occurs if the collision chart doesn't specify a rule regarding how to handle a 
certain collision. In this case, a custom runtime error is thrown. Another error could occur during
reflection, if one of the ActionParsers can't run reflection properly. In this case, a custom 
exception is thrown.

Part 2
How do you justify that your API is easy to learn?
* I justify that to check collisions between two objects, one must call onCollision on a
Collidable entity. This part is simple. However, one must also keep an updated list of current 
collisions that the entity has, which is extra work that the user must think about. 

How do you justify that API leads to readable code?
* APIs lead to readable code because they hide the nitty gritty details with API calls whose functionality
is understandable because of clarity in their names. One can intuit what their supposed to receive
/ what's supposed to happen as a result of calling the API without having to go through the code.

How do you justify that API is hard to misuse?
* I can justify that it's hard to misuse because it's pretty clear that you only call onCollision when
a Collision has occurred (also stated in documentation). And the parameters that are necessary are 
relatively clear.

Why do you think your API design is good (also define what your measure of good is)?
* I think my API design is decent, although it could be better. Unfortunately, one can't just call
onCollision over and over again without passing in the correct information regarding the other
entity being collided with and (potentially) the CollisionPhysicsInfo that must also be passed in.
One accidentally pass in a CollisionPhysicsInfo object when they're not supposed to, which would
break the code. I should look into making this part more robust.