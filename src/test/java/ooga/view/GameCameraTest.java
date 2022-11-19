package ooga.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCameraTest {




  @Test
  void testSetPlayerLocationNoMovement() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(100, 20);
    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
  }

  @Test
  void testSetPlayerLocationRightMovement() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(155, 20);
    assertEquals(55, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
  }

  @Test
  void testSetPlayerLocationLeftMovement() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(70, 20);
    assertEquals(45, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
  }

  @Test
  void testSetPlayerLocationUpMovement() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(100, 10);
    assertEquals(50, camera.getCameraX());
    assertEquals(5, camera.getCameraY());
  }

  @Test
  void testSetPlayerLocationDownMovement() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(100, 30);
    assertEquals(50, camera.getCameraX());
    assertEquals(15, camera.getCameraY());
  }


  @Test
  void testSetPlayerLocationTwoDirections() {
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.setPlayerLocation(155, 30);
    assertEquals(55, camera.getCameraX());
    assertEquals(15, camera.getCameraY());
  }

  @Test
  void testSetPlayerLocationStopAndStartScrolling(){
    GameCamera camera = new GameCamera(50, 10, new Margin(25, 100, 5, 0), 15, 200);

    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.stopScroll();
    camera.setPlayerLocation(155, 30);
    assertEquals(50, camera.getCameraX());
    assertEquals(10, camera.getCameraY());
    camera.startScroll();
    camera.setPlayerLocation(155, 30);
    assertEquals(55, camera.getCameraX());
    assertEquals(15, camera.getCameraY());
  }
}
