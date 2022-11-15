package ooga.model.entities;

import ooga.model.Info;
import ooga.model.entities.characters.Mario;
import ooga.model.entities.data.InitialAttributes;

public class BoringPlatform extends Entity{

    public BoringPlatform(int initialXCoordinate, int initialYCoordinate, double height, double width, Info entityInfo) {
        super(initialXCoordinate, initialYCoordinate, height, width, entityInfo);
    }

    @Override
    public double getXCoordinate() {
        return 0;
    }


}
