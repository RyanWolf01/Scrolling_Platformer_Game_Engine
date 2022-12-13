package ooga.controller.saveloadhandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ooga.controller.exceptions.SaveLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

public class CheckpointDirectory {
  public static final String SLASH = System.getProperty("file.separator");
  public static final String SAVED_GAMES = System.getProperty("user.dir") + SLASH + "src" + SLASH + "main" + SLASH
      + "resources" + SLASH + "savedgames" + SLASH;
  private String savedDirectoryName;
  private JSONObject collisionsJSON;
  private JSONObject controlsJSON;
  private JSONObject levelJSON;
  private static final Logger LOG = LogManager.getLogger(CheckpointDirectory.class);

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
    new File(SAVED_GAMES+savedDirectoryName).mkdirs();
    try {
      FileWriter levelFile = new FileWriter(SAVED_GAMES+savedDirectoryName+"/level.json");
      System.out.println(levelJSON.toJSONString());
      levelFile.write(levelJSON.toJSONString());
      levelFile.close();
      FileWriter controlFile = new FileWriter(SAVED_GAMES+savedDirectoryName+"/controls.json");
      controlFile.write(controlsJSON.toJSONString());
      controlFile.close();
      FileWriter collisionFile = new FileWriter(SAVED_GAMES+savedDirectoryName+"/collisions.json");
      collisionFile.write(collisionsJSON.toJSONString());
      collisionFile.close();

    } catch (IOException e) {
      LOG.error("Directory could not be saved");
      throw new SaveLoadException("file_saving_error");
    }
  }

}
