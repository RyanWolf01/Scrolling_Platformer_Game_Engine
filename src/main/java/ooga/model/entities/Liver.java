package ooga.model.entities;

public interface Liver extends Entity{
    /**
     * @return how many lives this thing has
     */
    int getLives();

    void incrementLives();

    void kill();
}
