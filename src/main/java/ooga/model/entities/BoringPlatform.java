package ooga.model.entities;

import ooga.model.Info;

public class BoringPlatform extends Entity{

    public BoringPlatform(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
        super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    }

    @Override
    public double getXCoordinate() {
        return 0;
    }


}
