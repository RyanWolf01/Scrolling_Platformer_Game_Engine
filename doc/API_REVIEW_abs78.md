# API Discussion

### Names

Arman Shekarriz (abs78), Jared Bank (jsb102)

In this discussion, I talked to Jared about specific APIs each of us have developed for the controller
side of our respective projects. While the APIs we discussed held a collection of public methods different
from one another, and performed different tasks (the one he showed me being responsible for interactions with the UI
and the interface and how it impacted changes in the game while the one I showed him being responsible for different JSON 
interactions and tools to create backend and frontend object from game data). One thing that I learned from
his implementation and found to be a good way to design the structure of his control was having a hierarchy of classes with an abstract class
for his basic controller methods that could be extended to controller classes responsible for changes in a game room vs. 
on the main screen vs. in other parts of the game. One of the things that we felt both of our implementations of the APIs did well
was encapsulate implementation decisions. By nature an API using to parse JSON information should be flexible and allow a use to simply
include information in their desired format and with the keys they want so long as they obey certain specific formatting rules. Outside of this,
the API can handle any kind of JSON information and change it into the information the game needs. In addition, one of the things both of us agreed
needed to be worked on more thoroughly was error handling, logging, and adapting and refactoring methods.
