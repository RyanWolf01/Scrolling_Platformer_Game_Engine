package ooga.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameCamera implements Camera {

  private double cameraX;
  private double cameraY;
  //TODO: Create System for setting camera size
  private double cameraWidth;
  private double cameraHeight;
  private Margin margin;
  private boolean scrollable = true;

  private static final Logger LOG = LogManager.getLogger(GameCamera.class);


  public GameCamera() {
    this(0, 0, new Margin(0, 0, 0, 0), 800, 1600);
  }

  public GameCamera(double startX, double startY, Margin startingMargin, int height, int width) {
    cameraX = startX;
    cameraY = startY;
    margin = startingMargin;
    cameraHeight = height;
    cameraWidth = width;
  }


  @Override
  public void setPlayerLocation(double playerX, double playerY) {
    if(!scrollable) return;

    if(! inHorizontalSpace(playerX)){
      double delta = generateDeltaX(playerX);
//      LOG.debug("Move Camera Horizontal" + delta);
      translateHorizontal(delta);
    }

    if(! inVerticalSpace(playerY)){
      double delta = generateDeltaY(playerY);
//      LOG.debug("Move Camera Horizontal" + delta);
      translateVertical(delta);
    }

  }

  @Override
  public double getCameraX() {
    return cameraX;
  }
  public double getCameraY(){
    return cameraY;
  }

  @Override
  public void stopScroll() {
    scrollable = false;
  }

  @Override
  public void startScroll() {
    scrollable = true;
  }

  private void translateHorizontal(double deltaX){
    cameraX += deltaX;
  }

  private void translateVertical(double deltaY){
    cameraY += deltaY;
  }

  private boolean inHorizontalSpace(double playerX){
    return (cameraX + margin.getLeftX() < playerX && playerX < cameraX + cameraWidth - margin.getRightX());
  }

  private boolean inVerticalSpace(double playerY){
    return (cameraY + margin.getTopY() < playerY && playerY < cameraY + cameraHeight - margin.getBottomY());
  }

  private double generateDeltaX(double playerX){

    double deltaLeft = playerX - (cameraX + margin.getLeftX());
    double deltaRight = playerX - (cameraX + cameraWidth - margin.getRightX());

    if (deltaLeft < 0 && deltaRight < 0){
      //to the left of the Horizontal Space
      return deltaLeft;
    } else if (deltaLeft > 0 && deltaRight > 0){
      //to the right of the Horizontal Space
      return deltaRight;
    } else{
      //Should not get here
      //TODO: Create Exception for this case
      return 0;
    }
  }

  private double generateDeltaY(double playerY){
    double deltaUp = playerY - (cameraY + margin.getTopY());
    double deltaDown = playerY - (cameraY + cameraHeight - margin.getBottomY());

    if (deltaUp < 0 && deltaDown < 0){
      //to the top of the Vertical Space
      return deltaUp;
    } else if (deltaUp > 0 && deltaDown > 0){
      //to the bottom of the Vertical Space
      return deltaDown;
    } else{
      //Should not get here
      //TODO: Create Exception for this case
      return 0;
    }
  }

}
