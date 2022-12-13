package ooga.view.screens;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.Main;
import ooga.view.nodes.HighScoreBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EndScreen {


  public static final int MAX_SCORES = 10;
  private final int sceneWidth = 400;
  private final int sceneHeight = 500;
  private final int titleHeight = 100;
  private GridPane levelPane;
  private File myScores;
  private int playerScore;
  private String playerName;
  private HashMap<Integer, String> scoresMap = new HashMap();
  private static final Logger LOG = LogManager.getLogger(EndScreen.class);
  private ResourceBundle languageResources;
  private HBox titleBox;

  /**
   * Creates an EndScreen To Display High Scores
   */
  public EndScreen(String language, int score, String name){
    playerScore = score;
    playerName = name;
    languageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
  }

  public Scene makeScene(String scoresPath){
    levelPane = new GridPane();

    levelPane.setId("Pane");
    levelPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    initiateScoreFile(scoresPath);
    createTitleBox();
    createTexts();

    Scene scene = new Scene(levelPane, sceneWidth, sceneHeight);
    return scene;
  }


  private void createTexts(){
    ArrayList<Integer> scoreList = new ArrayList<>(scoresMap.keySet());
    scoreList.sort(Comparator.reverseOrder());
    int boxHeight = (sceneHeight - titleHeight)/MAX_SCORES;
    for (int i = 0; i < Math.min(scoresMap.size(), MAX_SCORES); ++i) {
      HighScoreBox box = new HighScoreBox(sceneWidth, boxHeight, scoreList.get(i), scoresMap.get(scoreList.get(i)));
      levelPane.add(box, 0, i+1);
    }
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
    scores.add(createPlayerScoreObject());
    for (int i = 0; i < scores.size(); ++i) {
      JSONObject rec = (JSONObject) scores.get(i);
      String name = (String) rec.get("player");
      Integer score = Integer.parseInt((String) rec.get("score"));
      scoresMap.put(score, name);
    }
  }

  private JSONObject createPlayerScoreObject() {
    JSONObject singleScore = new JSONObject();
    singleScore.put("player", playerName);
    singleScore.put("score", playerScore);
    return singleScore;
  }

  private File createNewScoresFile(String scoresPath){
    JSONObject singleScore = createPlayerScoreObject();
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

  private void createTitleBox(){
    titleBox = new HBox();
    titleBox.setPrefWidth(sceneWidth);
    titleBox.setPrefHeight(titleHeight);
    Text highScoreTitle = new Text(languageResources.getString("high_scores"));
    titleBox.getChildren().add(highScoreTitle);
    levelPane.add(titleBox, 0, 0);
  }
}
