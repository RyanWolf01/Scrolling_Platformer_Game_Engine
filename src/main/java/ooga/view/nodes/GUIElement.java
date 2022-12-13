package ooga.view.nodes;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GUIElement extends HBox {

  Text GUIText;
  String elementName;

  private static final int MAX_WIDTH = 400;
  private static final int MAX_HEIGHT = 200;

  public GUIElement(String name, int initialValue){
    elementName = name;
    createText(initialValue);
    createBox();
    this.getChildren().add(GUIText);
  }

  private void createBox() {
    this.setWidth(MAX_WIDTH);
    this.setHeight(MAX_HEIGHT);
  }

  public void updateValue(int value){
    GUIText.setText(elementName + ": " + value);
  }

  private void createText(int initialValue){
    GUIText = new Text();
    updateValue(initialValue);
    GUIText.setFont(new Font(20));
  }
}
