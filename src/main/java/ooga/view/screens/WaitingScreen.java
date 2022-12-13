package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WaitingScreen implements Screen{
  private final int screenWidth = 400;
  private final int screenHeight = 400;

  public WaitingScreen(){
  }

  public Scene makeScene(){
    StackPane root = new StackPane();
    root.setPrefWidth(screenWidth);
    root.setPrefWidth(screenHeight);

    Text waitingText = new Text("Waiting For Database...");

    waitingText.setFont(new Font(30));
    waitingText.setFill(Color.LIMEGREEN);


    root.getChildren().add(waitingText);
    waitingText.setX(screenWidth/2 - waitingText.getBoundsInLocal().getWidth()/2);
    waitingText.setY(screenHeight/2 - waitingText.getBoundsInLocal().getHeight()/2);

    return new Scene(root, screenWidth, screenHeight);
  }
}
