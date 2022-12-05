package ooga.controller.saveloadhandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class CheckpointDirectory {
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String SAVED_GAMES = "savedgames/";
  private JSONObject collisionsJSON;
  private JSONObject controlsJSON;
  private JSONObject levelJSON;


  // link used here below, on writing json objects to files
  // https://crunchify.com/how-to-write-json-object-to-file-in-java/
  public CheckpointDirectory(String directoryName, JSONObject level, JSONObject controls, JSONObject collision) {
    new File(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName).mkdirs();
    try {
      FileWriter levelFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName+"/level.json");
      levelFile.write(level.toJSONString());
      FileWriter controlFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName+"/controls.json")
      controlFile.write(level.toJSONString());
      FileWriter collisionFile = new FileWriter(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName+"/collision.json");
      collisionFile.write(level.toJSONString());

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }





}
