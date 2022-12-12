package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class EndScreen {


  private final int levelWidth = 1200;

  private final int levelHeight = 600;
  private Pane levelPane;
  private File myScores;
  private HashMap<String, String> scoresMap = new HashMap();

  private static final Logger LOG = LogManager.getLogger(EndScreen.class);

  /**
   * Creates an EndScreen To Display High Scores
   */
  public EndScreen(){
  }

  public Scene makeScene(String scoresPath){
    levelPane = new Pane();

    levelPane.setId("Pane");
    levelPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    initiateScoreFile(scoresPath);
    createTexts();

    Scene scene = new Scene(levelPane, levelWidth, levelHeight);
    return scene;
  }


  private void createTexts(){

  }

  private void initiateScoreFile(String scoresPath){
    try{
      myScores = new File(this.getClass().getClassLoader().getResource(scoresPath).getFile());
      LOG.debug("Games List Loaded Successfully");
    } catch(Exception e){
      LOG.debug("No Scores File, Time to Create One");
      myScores = createNewScoresFile(scoresPath);
    }
    JSONObject scoresJson = null;
    try {
      scoresJson = (JSONObject) new JSONParser().parse(new FileReader(myScores));
    } catch (Exception e) {
      //TODO: Handle Exception
      throw new RuntimeException(e);
    }
    JSONArray scores = (JSONArray) scoresJson.get("scores");
    for (int i = 0; i < scores.size(); ++i) {
      JSONObject rec = (JSONObject) scores.get(i);
      String name = (String) rec.get("player");
      String score = (String) rec.get("score");
      scoresMap.put(name, score);
    }
  }

  private File createNewScoresFile(String scoresPath){
    JSONObject singleScore = new JSONObject();
    // TODO: Get Player Score From Controller and Player Name
    String playerName = "Player";
    int playerScore = 550;
    singleScore.put("player", playerName);
    singleScore.put("score", playerScore);
    JSONArray arr = new JSONArray();
    arr.add(singleScore);
    JSONObject newScoresObject = new JSONObject();
    newScoresObject.put("scores", arr);
    System.out.print(newScoresObject);

    try {
      FileWriter file = new FileWriter(scoresPath);
      file.write(newScoresObject.toJSONString());
      file.close();
    } catch (IOException e) {
      // TODO Actually implement Exception
      LOG.error("Could not save new Scores File");
      e.printStackTrace();
    }
    return new File(this.getClass().getClassLoader().getResource(scoresPath).getFile());
  }
}
