package ooga.model.entities.movement;

import java.util.LinkedList;
import java.util.Queue;
import ooga.model.actions.moveractions.MoverAction;

public class MovementQueue {

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
