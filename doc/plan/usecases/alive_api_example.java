/**
 * This is an example of how an event would be handled in which Mario (a subclass of Entity) steps
 * on a spiky turtle (another subclass of Entity) and thus he loses a life. In this scenario, the
 * methods in Alive and LifeDecrementer are called to first decrease Mario's lives and then check if
 * Mario is still alive. If not, he must be killed using the kill method in Alive.
 */

public void spikyTurtleInteraction(Character mario){ // assume Character class is a subclass of Entity that implements LifeDecrementer
    mario.decreaseLives(1); // decrease mario's lives counter by 1

    if(!mario.isAlive()){ // check if mario is now dead
      mario.kill(); // if mario has now died, kill him (disable abilities, set velocity to 0, etc.)
    }
}