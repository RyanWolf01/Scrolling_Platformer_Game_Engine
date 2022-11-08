/**
 * This is the interface to wrap the level Data into a package that shields the different parts of the current level data that only the model has access to. It is created by the controller at every step.
 */
public interface LevelDataWrapper {


    public Point2D.Double getPlayerLocation();

    //There will be other methods to get the different enemies and blocks, weather certain entities have died, etc.
    //Also, there will be more methods for getting score or new time values.
}