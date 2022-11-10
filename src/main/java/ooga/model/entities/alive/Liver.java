package ooga.model.entities.alive;

public interface Liver{
    /**
     * @return how many lives this thing has
     */
    int getLives();

    void incrementLives();

    void kill();
}
