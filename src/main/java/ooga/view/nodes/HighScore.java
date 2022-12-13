package ooga.view.nodes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HighScore extends AnchorPane {

  private static final int FONT_SIZE = 25;

  public HighScore(int width, int height, int score, String name){
    this.setMinWidth(width);
    this.setMinHeight(height);
    createTexts(score, name);
  }

  private void createTexts(int score, String name) {
    Text scoreText = new Text(Integer.toString(score));
    setTextStyle(scoreText);
    Text nameText = new Text(name);
    setTextStyle(nameText);
    this.getChildren().addAll(nameText, scoreText);
    AnchorPane.setLeftAnchor(nameText, 0.0);
    AnchorPane.setRightAnchor(scoreText, 0.0);
  }

  private void setTextStyle(Text text){
    text.setFill(Color.PINK);
    text.setFont(new Font(FONT_SIZE));
  }

}
