package ooga.view.nodes;

import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.controller.exceptions.BadImageException;

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
    update(xCoordinate, yCoordinate, height, width);

    try {
      this.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(url))));
    } catch(RuntimeException e){
      throw new BadImageException("bad_url", e);
    }
  }

  public void update(double xCoordinate, double yCoordinate, double height, double width){
    backX = xCoordinate;
    backY = yCoordinate;
    this.setX(xCoordinate);
    this.setY(yCoordinate);
    this.setFitWidth(width);
    this.setFitHeight(height);
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
