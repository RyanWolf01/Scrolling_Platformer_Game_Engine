package ooga.controller.saveloadhandling;

import java.io.File;

public class CheckpointDirectory {
  private static final String RESOURCE_DIRECTORY = "/";
  private static final String SAVED_GAMES = "savedgames/";


  public CheckpointDirectory(String directoryName, ) {
    new File(RESOURCE_DIRECTORY+SAVED_GAMES+directoryName).mkdirs();

  }





}
