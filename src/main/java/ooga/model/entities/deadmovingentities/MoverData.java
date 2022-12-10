package ooga.model.entities.deadmovingentities;

import java.util.ResourceBundle;
import ooga.model.entities.info.ImmutableInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoverData {

  private static final Logger LOG = LogManager.getLogger(MoverData.class);
  private final int SCREEN_SIZE;

  public MoverData(ImmutableInfo entityInfo){
    int tempScreenSize;
    try{
      tempScreenSize = Integer.parseInt(
          ResourceBundle.getBundle("properties/view").getString("screen_size"));
    } catch(NumberFormatException exception){
      LOG.error("screen size from properties file formatted incorrectly");
      throw exception;
    }

    SCREEN_SIZE = tempScreenSize;
  }

}
