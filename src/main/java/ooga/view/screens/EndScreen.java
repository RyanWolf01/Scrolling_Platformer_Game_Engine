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

import java.io.*;
import java.util.*;

public class EndScreen {


  public static final int MAX_SCORES = 10;
  private final int sceneWidth = 400;
  private final int sceneHeight = 500;
  private final int titleHeight = 100;
  private GridPane levelPane;
  private FileReader myScores;
  private Map<Integer, String> scoresMap;
  private static final Logger LOG = LogManager.getLogger(EndScreen.class);
  private ResourceBundle languageResources;
  private HBox titleBox;

  /**
   * Creates an EndScreen To Display High Scores
   */
  public EndScreen(String language, Map<Integer, String> scores){
    scoresMap = scores;
    languageResources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
  }

  public Scene makeScene(){
    levelPane = new GridPane();

    levelPane.setId("Pane");
    levelPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    addTitleBox();
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

/**
* initiateScoreFile was from when the scores were stored locally.
 * Deprecated because scores are now taken from database and the map does not need to be created in EndScreen
 * @param scoresPath
*/
  @Deprecated
  private void initiateScoreFile(String scoresPath){
    LOG.debug(scoresPath);
    try{
      myScores = new FileReader(scoresPath);
      LOG.debug(myScores);
      LOG.debug("Games List Loaded Successfully");
    } catch(Exception e){
      LOG.error(e.getMessage());
      LOG.debug("No Scores File, Time to Create One");
      myScores = createNewScoresFile(scoresPath);
    }
    JSONObject scoresJson = null;
    try {
      scoresJson = (JSONObject) new JSONParser().parse(myScores);
    } catch (Exception e) {
      //TODO: Handle Exception
      throw new RuntimeException(e);
    }
    JSONArray scores = (JSONArray) scoresJson.get("scores");
    scores.add(createPlayerScoreObject("Player", 0));
    for (int i = 0; i < scores.size(); ++i) {
      JSONObject rec = (JSONObject) scores.get(i);
      String name = (String) rec.get("player");
      Integer score = Integer.parseInt((String) rec.get("score"));
      scoresMap.put(score, name);
    }
  }

/**
* playerScore Objects do not need to be created because it is now done on the database
 * @param playerName
 * @param playerScore
 * @return
*/
  @Deprecated
  private JSONObject createPlayerScoreObject(String playerName, int playerScore) {
    JSONObject singleScore = new JSONObject();
    singleScore.put("player", playerName);
    singleScore.put("score", playerScore);
    return singleScore;
  }

/**
* Deprecated because the ScoresFile is not created locally anymore
 * @param scoresPath
 * @return a new FileReader of the created JSON
*/
  @Deprecated
  private FileReader createNewScoresFile(String scoresPath){
    JSONObject singleScore = createPlayerScoreObject("Player", 0);
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
    }
    FileReader newFile;
    try {
      newFile = new FileReader(scoresPath);
    } catch (FileNotFoundException e) {
      newFile = null;
      // TODO Actually implement Exception
      LOG.error("Could not load new Scores File");
    }

    return newFile;
  }

  private void addTitleBox(){
    titleBox = new HBox();
    titleBox.setPrefWidth(sceneWidth);
    titleBox.setPrefHeight(titleHeight);
    Text highScoreTitle = new Text(languageResources.getString("high_scores"));
    highScoreTitle.setFill(Color.SNOW);
    titleBox.getChildren().add(highScoreTitle);
    levelPane.add(titleBox, 0, 0);
  }
}
