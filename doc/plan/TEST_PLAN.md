Describe at least two specific strategies your team discussed to make your APIs more easily testable
(such as useful parameters and return values, using smaller classes or methods, throwing Exceptions,
etc.). Describe at least three test scenarios per team member for a project feature they plan to be 
responsible for (at least one of which is negative/sad/error producing), the expected outcome, and 
how your design supports testing for it (such as methods you can call, specific values you can use 
as parameters and return values to check, specific expected UI displays or changes that could be 
checked, etc.). A test scenario describes the the expected results from a user's action to initiate
a feature in your program and the steps that lead to that result (whether "happy" or"sad").


1. One thing we can do is to make mock classes for parameters that we need to pass to the API. Then we
can provide those mock classes with fixed values that can make testing significantly easier.

2. Another thing we can do is

Example test cases:

Ryan:

    Test: CollisionInformation detectCollision(Entity entityA, Entity entityB)
    Set the position of entityA and entityB to intersect each other such that they're colliding on
    each other's side. Call detectCollision(entityA, entityB) and check the collisionOccured property
    of the CollisionInformation object that's returned to make sure it's true. Also check to see
    if the CollisionInformation returns the string "SIDE".

    Test: CollisionChart loadCollisionChart(String entityType)
    Load in a JSON object dynamically with hardcoded values. Then send the correct entityType string
    to the method. Step through all the values inside CollisionChart (which encapsulates a JSON 
    object) and make sure that they match with the JSON object originally loaded.

    Test: void handleCollision(CollisionInformation collisionInformation, Entity entityA, Entity entityB)
    Test to make sure that the state of entityA changes how it's supposed to after colliding with
    entityB, given that you know the rules for how entityA is supposed to react to collisions. For
    example, make sure that entityA moves by 10 pixels if an EntityCommand to move entityA by 10 
    pixels is executed on it. Also write a test such that no match occurs/no criteria exist with
    entityA to handle some sort of collision, and ensure that a NoCriteriaMatched exception is thrown.
