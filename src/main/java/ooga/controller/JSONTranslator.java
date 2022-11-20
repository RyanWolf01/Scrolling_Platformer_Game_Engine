package ooga.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import ooga.model.entities.containers.EntityContainer;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface JSONTranslator {

  JSONObject initialJSONInformation(String JSONFilePath) throws IOException, ParseException;

  void makeEntityContainerFromLevelJSON(String JSONFilePath, ConnectionContainer connectionContainer);

}
