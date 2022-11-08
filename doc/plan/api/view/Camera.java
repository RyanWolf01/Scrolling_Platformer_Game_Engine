


public interface Camera {


  public void setPlayerLocation(Point2D.Double playerLocation);

  public Point2D.Double getCameraLocation();
  public void stopScroll();
  public void startScroll();
}