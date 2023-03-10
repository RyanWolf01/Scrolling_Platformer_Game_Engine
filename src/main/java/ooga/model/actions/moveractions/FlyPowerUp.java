package ooga.model.actions.moveractions;

import java.util.ResourceBundle;
import ooga.controller.exceptions.MovementDataException;
import ooga.model.entities.movement.Mover;
import ooga.model.entities.movement.MoverData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlyPowerUp implements MoverAction {

  private static final Logger LOG = LogManager.getLogger(FlyPowerUp.class);

  private final double FLY_MULTIPLIER;

  public FlyPowerUp(){
    double tempVelocity;
    try{
      tempVelocity = Double.parseDouble(
          ResourceBundle.getBundle("properties/powerups").getString("fly_multiplier"));
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
    moverData.setGravityVelocity(moverData.getGravityVelocity() / FLY_MULTIPLIER);
  }

}
