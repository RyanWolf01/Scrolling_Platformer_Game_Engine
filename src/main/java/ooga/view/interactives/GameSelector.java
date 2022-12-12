package ooga.view.interactives;

import javafx.scene.control.ComboBox;
import ooga.view.screens.StartScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameSelector extends ComboBox<String> {


  private static final String GAMES_FILE = "games.json";
  private static final String[] DEFAULT_GAME_LIST = {"Super Mario Bros", "Doodle Jump", "Geometry Dash"};
  private static final String CHOOSE_GAME_PROMPT = "Select a Game";
  private String selectedGame;
  private StartScreen myStartScreen;
  private static final Logger LOG = LogManager.getLogger(GameSelector.class);
  private ArrayList<String> gamesArray = new ArrayList<>();
  private HashMap<String, String> gameToDirectory = new HashMap<>();


  public GameSelector(StartScreen startScreen){
    super();
    myStartScreen = startScreen;
    getGamesListFile();
  }

  private void getGamesListFile(){
    JSONObject levelsJson;
    try {
      File gamesListFile = new File(this.getClass().getClassLoader().getResource(GAMES_FILE).getFile());
      LOG.debug("Games List Loaded Successfully");
      levelsJson = (JSONObject) new JSONParser().parse(new FileReader(gamesListFile));
      JSONArray games = (JSONArray) levelsJson.get("games");
      for (int i = 0; i < games.size(); ++i) {
        JSONObject rec = (JSONObject) games.get(i);
        String name = (String) rec.get("name");
        String folder = (String) rec.get("folder");
        gamesArray.add(name);
        gameToDirectory.put(name, folder);
      }
    } catch (Exception e) {
      LOG.error("No gamesList File");
      gamesArray = new ArrayList<>(List.of(DEFAULT_GAME_LIST));
    }
    createLevelDirectoryMap();
    addGamesToPrompt();
  }

  private void addGamesToPrompt(){
    this.setPromptText(CHOOSE_GAME_PROMPT);
    this.getItems().addAll(gamesArray);
    this.setOnAction(event ->{
      selectedGame = this.getValue();
      myStartScreen.changeBackground(selectedGame + ".png");
      this.setPromptText(selectedGame);
      myStartScreen.changeLevelSelectionButtonVisible();
    });
  }

  private void createLevelDirectoryMap(){
    String[] games = {"mario/", "doodle/", "dash/"};
    for (int i = 0; i < games.length; i++) {
      gameToDirectory.put(gamesArray.get(i), games[i]);
    }
  }
}
