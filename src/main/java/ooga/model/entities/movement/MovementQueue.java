package ooga.model.entities.movement;

import java.util.LinkedList;
import java.util.Queue;
import ooga.model.actions.moveractions.MoverAction;
import ooga.model.entities.entitymodels.MovingCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovementQueue {

  private static final Logger LOG = LogManager.getLogger(MovementQueue.class);

  private Queue<MoverAction> movementQueue;

  /**
   * holds MoverActions to be made continuously by AutomaticMoverBehavior Movers
   */
  public MovementQueue(){
    movementQueue = new LinkedList<>();
  }

  /**
   *
   * @return current move in the movement queue and add this current move back to the queue
   */
  public MoverAction nextMove(){
    MoverAction currMove = movementQueue.poll();
    movementQueue.add(currMove); // add back currMove in the cycle of moves
    return currMove;
  }

  /**
   * add MoverAction move to the queue
   *
   * @param move MoverAction to be added
   */
  public void addMove(MoverAction move){
    movementQueue.add(move);
  }

}
