package ooga.model.entities;

public class BoringPlatform implements Entity{
    EntityInfo myInfo;
    public BoringPlatform(EntityInfo entityInfo) {
        this.myInfo = entityInfo;
    }
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

    @Override
    public EntityInfo getImmutableEntityInfo() {
        return myInfo;
    }
}
