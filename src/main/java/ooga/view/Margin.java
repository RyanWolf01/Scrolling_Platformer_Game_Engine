package ooga.view;

/**
* Margin Class is used to describe the margins for the camera that the main character is allowed to be inside.
*/
public class Margin {

  /**
   * Each of these four variables describe how much space from that side of the screen that the player can be in
   */
  private double leftX;
  private double rightX;
  private double topY;
  private double bottomY;


  public Margin(double left, double right, double top, double bottom){
    leftX = left;
    rightX = right;
    topY = top;
    bottomY = bottom;
  }

  public double getLeftX() {
    return leftX;
  }

  public double getRightX() {
    return rightX;
  }

  public double getTopY() {
    return topY;
  }

  public double getBottomY() {
    return bottomY;
  }
}
