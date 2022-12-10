package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.deadmovingentities.Mover;
import ooga.model.entities.deadmovingentities.MoverData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FlyPowerUp implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(FlyPowerUp.class);

  private final int FLY_MULTIPLIER;

  public FlyPowerUp(){
    int tempVelocity;
    try{
      tempVelocity = Integer.parseInt(
          ResourceBundle.getBundle("properties/movement").getString("fly_multiplier"));
    }
    catch(NumberFormatException exception){
      throw new MovementDataException("incorrect velocity format", exception);
    }
    FLY_MULTIPLIER = tempVelocity;
  }

  /**
   * moves entity up in y direction by a fixed amount
   * @param entity
   */
  @Override
  public void execute(Mover entity){
    MoverData moverData = entity.getMoverData();
    moverData.setUpwardActionVelocity(FLY_MULTIPLIER * moverData.getUpwardActionVelocity());
    moverData.setDownwardActionVelocity(moverData.getDownwardActionVelocity() / (FLY_MULTIPLIER));
  }

}
