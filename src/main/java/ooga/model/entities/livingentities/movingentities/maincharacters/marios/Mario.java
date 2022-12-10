package ooga.model.entities.livingentities.movingentities.maincharacters.marios;
import ooga.model.actionparsers.EndGameActionParser;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.info.Info;
import ooga.model.actionparsers.AliveActionParser;
import ooga.model.actionparsers.MoverActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.entities.livingentities.movingentities.maincharacters.MainCharacterEntity;
import ooga.model.entities.modelcallers.GameEnder;
import ooga.model.entities.modelcallers.functionalinterfaces.EndGameCallable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mario extends MainCharacterEntity {

  private static final Logger LOG = LogManager.getLogger(Mario.class);
  private EndGameCallable endGameMethod;

  /**
   * Mario has lives, can move, and takes user input. Specific Mario class was created to handle off-screen inputs uniquely
   * @param chart Collision Chart
   * @param initialXCoordinate initial x
   * @param initialYCoordinate initial y
   * @param height height
   * @param width width
   * @param entityInfo entity info
   */
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
    if(getXCoordinate() <= 0){
      setXCoordinate(0);
      LOG.info("Mario off left of screen.");
    }
    if(getXCoordinate() >= getScreenSize()){
      setXCoordinate(getScreenSize()-1);
      LOG.info("Mario off right of screen.");
    }
  }

  /**
   * reads from CollisionChart and performs resulting actions necessary to handle the collision
   *
   * @param actionDataContainer
   */
  @Override
  public int performActions(ActionDataContainer actionDataContainer) {
    int count = 0;
    count += new MoverActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
    count += new EndGameActionParser(actionDataContainer).parseAndApplyActions(this);

    return count;
  }

}
