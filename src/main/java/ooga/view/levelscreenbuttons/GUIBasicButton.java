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


public abstract class GUIBasicButton {

  public static final String GUI_ICON_PROPERTIES = "properties.GUIButtons";
  public static final ResourceBundle ICON_PROPERTIES = ResourceBundle.getBundle(GUI_ICON_PROPERTIES);

  private Button button;
  private Text buttonText;
  private VBox buttonContainer;




  public GUIBasicButton(String buttonText, String iconID) {

    // load the graphic using resources
    ResourceBundle iconResources = ResourceBundle.getBundle(GUI.GUI_ICON_PROPERTIES);
    InputStream iconPath = GUIButton.class.getClassLoader().getResourceAsStream(
        iconResources.getString(iconID));
    buttonContainer = new VBox();

    // set icon graphic appearances
    if (iconPath == null) {
      throw new NullPointerException("Invalid button icon path");
    }
    icon = new ImageView(new Image(iconPath));
    icon.setFitWidth(properties.getButtonProperty("defaultGraphicWidth"));
    icon.setFitHeight(properties.getButtonProperty("defaultGraphicHeight"));

    // set up button
    button = new Button();
    button.setGraphic(icon);

    this.buttonText = new Text(buttonText);
    this.buttonText.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
    buttonContainer.getChildren().addAll(button, this.buttonText);
    buttonContainer.setAlignment(Pos.CENTER);
  }


}
