package ooga.view.nodes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.model.entities.EntityInfo;

/**
 * The ScrollingNode object extends the JavaFX ImageView object.
 * Every node that is displayed is going to be a ScrollingNode class
 * object.
 */
public class ScrollingNode extends ImageView {
  public ScrollingNode(int xCoordinate, int yCoordinate, double height, double width, String url){
    super();
    this.setX(xCoordinate);
    this.setY(yCoordinate);
    this.setFitHeight(height);
    this.setFitWidth(width);

    this.setImage(new Image(url));

  }

}
