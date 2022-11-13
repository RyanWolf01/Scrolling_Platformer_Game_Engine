package ooga.model.entities;

import ooga.model.Info;
import ooga.model.entities.data.InitialAttributes;

public class BoringPlatform extends Entity{

    public BoringPlatform(InitialAttributes attributes, Info entityInfo, double length, double width) {
        super(attributes, entityInfo, length, width);
    }

    @Override
    public double getXCoordinate() {
        return 0;
    }
}
