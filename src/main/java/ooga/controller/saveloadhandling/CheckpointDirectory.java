package ooga.controller.saveloadhandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class CheckpointDirectory {
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String SAVED_GAMES = "savedgames/";
  private String savedDirectoryName;
  private JSONObject collisionsJSON;
  private JSONObject controlsJSON;
  private JSONObject levelJSON;


  // link used here below, on writing json objects to files
  // https://crunchify.com/how-to-write-json-object-to-file-in-java/

  /**
   * Default constructor for a CheckpointDirectory, which is what will be saved in resources when a user presses save game
   * and then inputs what they want the directory to be called, which is the name it will be saved as
   * @param directoryName
   * @param level
   * @param controls
   * @param collision
   */
  public CheckpointDirectory(String directoryName, JSONObject level, JSONObject controls, JSONObject collision) {
    this.savedDirectoryName = directoryName;
    this.levelJSON = level;
    this.controlsJSON = controls;
    this.collisionsJSON = collision;
  }

  /**
   * Method to create the directory once one decides they want to save the game or load the game
   * Used to actually make the new directory and add the JSON files that save the game state to this directory
   * in the savedgames directory within resources
   */
  public void CreateDirectory() {
    new File(RESOURCE_DIRECTORY+SAVED_GAMES+savedDirectoryName).mkdirs();
    try {
      FileWriter levelFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+savedDirectoryName+"/level.json");
      levelFile.write(levelJSON.toJSONString());
      FileWriter controlFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+savedDirectoryName+"/controls.json");
      controlFile.write(controlsJSON.toJSONString());
      FileWriter collisionFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+savedDirectoryName+"/collision.json");
      collisionFile.write(collisionsJSON.toJSONString());

    } catch (IOException e) {
      // TODO: handle exception and log
      throw new RuntimeException(e);
    }
  }

}
