package ooga.model.entities.livingentities;

import ooga.model.actionparsers.AliveActionParser;
import ooga.model.collisions.actiondata.ActionDataContainer;
import ooga.model.collisions.collisionhandling.CollisionChart;
import ooga.model.entities.collidable.CollidableEntity;
import ooga.model.entities.info.Info;

public class LivingStaticCollidable extends CollidableEntity implements Alive {
    private AliveBehavior aliveBehavior;

    public LivingStaticCollidable(CollisionChart collisionChart, int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
        super(collisionChart, initialXCoordinate, initialYCoordinate, height, width, entityInfo);
        aliveBehavior = new AliveBehavior(entityInfo);
    }

    @Override
    protected int performActions(ActionDataContainer actionDataContainer) {
        return new AliveActionParser(actionDataContainer).parseAndApplyActions(this);
    }

    @Override
    public int getLives() {
        return aliveBehavior.getLives();
    }

    @Override
    public void kill() {
        aliveBehavior.kill();
    }

    @Override
    public void changeLives(int changeInLives) {
        aliveBehavior.changeLives(changeInLives);
    }
}
