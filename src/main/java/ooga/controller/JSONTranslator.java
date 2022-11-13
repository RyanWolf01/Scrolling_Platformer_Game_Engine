package ooga.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface JSONTranslator {

  public JSONObject initialJSONInformation(String JSONFilePath) throws IOException, ParseException;
}
