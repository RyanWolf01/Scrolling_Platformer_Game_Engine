package ooga.controller;

/**
 * Class that translates
 */
public class CoordinateTranslator {
    private int cameraHeight;
    private int cameraWidth;
    public CoordinateTranslator(int cameraHeight, int cameraWidth){
        this.cameraHeight = cameraHeight;
        this.cameraWidth = cameraWidth;
    }

    public int translateY(int backendYCoord, double entityHeight){
        //System.out.println(600 - backendYCoord - entityHeight);
        return (int) (600 - backendYCoord - entityHeight);
    }
}
