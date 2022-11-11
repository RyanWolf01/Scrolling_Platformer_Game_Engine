package ooga.model.entities;

import ooga.model.entities.data.InitialAttributes;

public class BoringPlatform extends Entity{

    public BoringPlatform(InitialAttributes attributes, EntityInfo entityInfo) {
        super(attributes, entityInfo);
    }

    @Override
    public int getXCoordinate() {
        return 0;
    }
}
