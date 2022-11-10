package ooga.model.entities;

public interface Liver{
    /**
     * @return how many lives this thing has
     */
    int getLives();

    void incrementLives();

    void kill();
}
