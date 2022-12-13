package ooga.view.screens;


import javafx.scene.Scene;

/** All screens should use this interface because it is expected that once the Screen is initialized,
 * all is needed is to invoke the method that does makeScene to create and get the Scene
 */
public interface Screen {


/**
* Once the Screen has been initialized, this method creates the Scene because all variables are already set
*/
  Scene makeScene();

}
