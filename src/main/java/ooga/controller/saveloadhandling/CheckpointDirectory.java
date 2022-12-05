package ooga.controller.saveloadhandling;

import java.io.File;
import org.json.simple.JSONObject;

public class CheckpointDirectory {
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String SAVED_GAMES = "savedgames/";
  private JSONObject collisionsJSON;
  private JSONObject controlsJSON;
  private JSONObject levelJSON;


  public CheckpointDirectory(String directoryName, JSONObject level, JSONObject controls, JSONObject collision) {
    new File(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName).mkdirs();

  }





}
