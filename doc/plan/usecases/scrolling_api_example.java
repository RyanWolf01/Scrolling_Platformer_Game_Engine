/**
 * This is an example of what could happen when a player moves outside of the movable area of the viewable area. During the frame generation of the view, all objects will be moves to fit to the camera's origin.
 */

private View myView;
private Camera myCamera;
private EntityGroup myEntityGroup;

private stepView(){
    // Get the levelData from the Controller
    LevelDataWrapper levelData = new LevelDataWrapper();
    Point2D.Double marioLocation = levelData.getMarioLocation();
    myView.step(levelData);
    // When setPlayerLocation is called, the Camera will do the scroll, detailed in second method
    myCamera.setPlayerLocation(marioLocation);

}

public void setPlayerLocation(Point2D.Double playerLocation){
        if(playerOutsideMargin(playerLocation)){
          double deltaX = margin.right - (playerLocation.x + playerWidth);
          double deltaY = margin.left - (playerLocation.y + playerHeight);
          myView.getEntityGroup().moveGroup(deltaX, deltaY);
        }
}