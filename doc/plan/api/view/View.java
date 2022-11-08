

public interface View {


  public void addScore(int additionalScore);

  public void addLife();
  public void loseLife();
  public void addGuiElement(Node newElement, double xLocation, double yLocation);
  public void startLevel(LevelDataWrapper levelData);
}