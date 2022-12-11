package ooga.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONObject;

public class DatabaseAccess {
  public static final String DATABASE_URL = "https://scrolling-plagtform-default-rtdb.firebaseio.com/";
  String game;

  public DatabaseAccess(String game){
    this.game = game;
  }

  private JSONObject getHighScores(){
    HttpURLConnection con = null;
    JSONObject result = null;

    try {
      con = (HttpURLConnection) new URL(DATABASE_URL+"high_scores/"+game+".json").openConnection();
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/json");

      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();

      String received = content.toString();

      result = new JSONObject(received);
      System.out.println(result);


    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return result;
  }

  public void postHighScore(String username, int score){
    JSONObject high_scores = getHighScores();

    high_scores.put(username, score);

    HttpURLConnection con = null;
    try {
      con = (HttpURLConnection) new URL(DATABASE_URL+"high_scores/"+game+".json").openConnection();
      con.setRequestMethod("PUT");
      con.setDoOutput(true);
      con.setRequestProperty("Content-Type", "application/json");

      OutputStreamWriter out = new OutputStreamWriter(
          con.getOutputStream());
      out.write(high_scores.toString());
      out.close();

      con.getInputStream();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String highestScorer(){
    JSONObject json = getHighScores();

    String highestScorer = null;
    int highestScore = 0;

    for (Iterator<String> it = json.keys(); it.hasNext(); ) {
      String key = it.next();
      int score = (int) json.get(key);

      if(score > highestScore){
        highestScorer = key;
        highestScore = score;
      }
    }

    return highestScorer;
  }

  public int highestScore(){
    JSONObject json = getHighScores();

    return (int) json.get(this.highestScorer());
  }

}
