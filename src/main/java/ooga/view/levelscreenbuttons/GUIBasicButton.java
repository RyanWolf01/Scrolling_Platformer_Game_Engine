package ooga.view.levelscreenbuttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.io.InputStream;
import java.util.ResourceBundle;
import ooga.Main;
import ooga.controller.GameController;


public abstract class GUIBasicButton {

  public static final String GUI_ICON_PROPERTIES = "properties.GUIButtons";
  public static final ResourceBundle ICON_PROPERTIES = ResourceBundle.getBundle(GUI_ICON_PROPERTIES);

  private Button button;
  private Text buttonText;
  private VBox buttonContainer;
  private ImageView buttonIcon;
  private GameController myController;


  public GUIBasicButton(String buttonText, String iconString, GameController controller) {

    this.myController = controller;
    InputStream iconPath = GUIBasicButton.class.getClassLoader().getResourceAsStream(
        ICON_PROPERTIES.getString(iconString));
    buttonContainer = new VBox();

    // set icon graphic appearances
    if (iconPath == null) {
      throw new NullPointerException("Invalid button icon path");
    }

    // TODO: fix magic values**
    buttonIcon = new ImageView(new Image(iconPath));
    buttonIcon.setFitWidth(50);
    buttonIcon.setFitHeight(50);

    // set up button
    button = new Button();
    button.setGraphic(buttonIcon);

    this.buttonText = new Text(buttonText);
    this.buttonText.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
    buttonContainer.getChildren().addAll(button, this.buttonText);
    buttonContainer.setAlignment(Pos.CENTER);
  }

  /**
   * Sets the event to occur when the button is clicked on
   *
   * @param actionEvent method to run when the button is pressed
   */
  protected void setOnClickEvent(EventHandler<ActionEvent> actionEvent) {
    button.setOnAction(actionEvent);
  }

  public Node getButton() {
    return buttonContainer;
  }

}
