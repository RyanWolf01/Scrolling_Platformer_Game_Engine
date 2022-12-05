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
  public CheckpointDirectory(String directoryName, JSONObject level, JSONObject controls, JSONObject collision) {
    this.savedDirectoryName = directoryName;
    this.levelJSON = level;
    this.controlsJSON = controls;
    this.collisionsJSON = collision;
  }

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
