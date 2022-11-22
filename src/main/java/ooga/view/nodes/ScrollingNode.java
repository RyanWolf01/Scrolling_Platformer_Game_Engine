package ooga.view.nodes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ScrollingNode object extends the JavaFX ImageView object.
 * Every node that is displayed is going to be a ScrollingNode class
 * object.
 */
public class ScrollingNode extends ImageView {

  private double backX;
  private double backY;
  public ScrollingNode(int xCoordinate, int yCoordinate, double height, double width, String url){
    super();
    update(xCoordinate, yCoordinate);
    this.setFitHeight(height);
    this.setFitWidth(width);

    this.setImage(new Image(url));
  }

  public void update(double xCoordinate, double yCoordinate){
    backX = xCoordinate;
    backY = yCoordinate;
  }

  public double getBackX(){
    return backX;
  }
  public double getBackY(){
    return backY;
  }

  public void updateCameraX(double cameraX){
    this.setX(backX - cameraX);
  }

  public void updateCameraY(double cameraY){
    this.setY(backY - cameraY);
  }

}
