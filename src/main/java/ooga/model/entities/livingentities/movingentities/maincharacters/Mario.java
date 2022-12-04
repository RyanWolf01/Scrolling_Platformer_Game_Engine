package ooga.model.entities.livingentities.movingentities.maincharacters;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mario extends MainCharacterEntity {

  private static final Logger LOG = LogManager.getLogger(Mario.class);

  public Mario(CollisionChart chart, int initialXCoordinate, int initialYCoordinate, double height, double width,
      Info entityInfo) {
    super(chart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
  }

  /**
   * helper method called in move() that handles what happens to this entity when it goes off the screen
   */
  @Override
  protected void handleInvalidCoordinates() {
    if(getYCoordinate() >= getScreenSize()){
      kill();
      LOG.info("Mario was killed.");
    }
    if(getYCoordinate() <= 0){
      setYCoordinate(1);
      LOG.info("Mario off top of screen.");
    }
    if(getXCoordinate() <= 0){
      setXCoordinate(1);
      LOG.info("Mario off left of screen.");
    }
    if(getXCoordinate() >= getScreenSize()){
      setXCoordinate(getScreenSize()-1);
      LOG.info("Mario off right of screen.");
    }
  }

  /**
   * Implements method in Alive interface that changes object's lives
   *
   * @param changeInLives is the change in lives
   */
  @Override
  public void changeLives(int changeInLives) {
    setLives(getLives() + changeInLives);
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   *
   * @param actionDataContainer
   */
  /*
    TODO: Right now this only works if there is one Action of each type (Alive and Mover). Make
    TODO: it so that if there are multiple MoverActions, it will perform all the actions. Also,
    TODO: make sure that the system throws an error if there are actions that can't be parsed by
    TODO: either of these parsers (e.g. of type FooAction, where Mario doesn't implement the
    TODO: interface Foo).

    TODO: Also, this code seems pretty general and probably shouldn't be implemented by Mario.
    TODO: It would be ideal if Mario inherited methods to perform AliveActions and MoverActions
    TODO: from somewhere else, like AliveAction and MoverAction abstract classes. But of course,
    TODO: Mario can't implement multiple abstract classes. So perhaps MainCharacter could implement
    TODO: these? Not really sure.

    TODO: Also, some of the code is duplicated in the ActionParser classes, which should be addressed
    TODO: Perhaps they could inherit from a shared abstract ActionParser class, but we just couldn't
    TODO: refer to the them in here as ActionParsers because their .getAction() classes would return
    TODO: different types (regarding those changes, nothing would change from the perspective of
    TODO: this file).

    Also need to add testing and javadoc of course.
   */
  @Override
  public int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }


}
