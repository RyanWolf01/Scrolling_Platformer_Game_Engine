package ooga.controller;

import java.io.FileNotFoundException;
import org.json.simple.JSONObject;

public interface JSONTranslator {

  public JSONObject initialJSONInformation(String JSONFilePath) throws FileNotFoundException;
}
