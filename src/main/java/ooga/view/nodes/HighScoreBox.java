package ooga.view.nodes;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class HighScoreBox extends HBox {

  public HighScoreBox(int width, int height, int score, String name){
    this.setWidth(width);
    this.setHeight(height);
    createTexts(score, name);
  }

  private void createTexts(int score, String name) {
    Text scoreText = new Text(this.getWidth()/2, 0, Integer.toString(score));
    Text nameText = new Text(0, 0, name);
    this.getChildren().addAll(scoreText, nameText);
  }
}
