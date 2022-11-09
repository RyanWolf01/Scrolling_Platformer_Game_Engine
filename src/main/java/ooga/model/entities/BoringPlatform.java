package ooga.model.entities;

public class BoringPlatform implements Entity{
    @Override
    public int getXCoordinate() {
        return 0;
    }

    @Override
    public int getYCoordinate() {
        return 0;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.BORING_PLATFORM;
    }
}
