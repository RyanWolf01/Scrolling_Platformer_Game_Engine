package ooga.controller;

import ooga.controller.exceptions.BadDatabaseAccessException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseAccessTest {
    DatabaseAccess access;

    @Test
    public void constructorTest(){
        access = new DatabaseAccess("mario");
        assertInstanceOf(DatabaseAccess.class, access);
    }

    @Test
    public void getHighestScorerTest(){
        access = new DatabaseAccess("mario");
        String scorer = access.highestScorer();

        assertEquals(scorer, "test");
    }

    @Test
    public void getHighestScoreTest(){
        access = new DatabaseAccess("mario");
        int score = access.highestScore();

        assertEquals(score, 459);
    }

    @Test
    public void putNewScoreTest(){
        access = new DatabaseAccess("some_game");
        access.postHighScore("thomsen", 69);

        JSONObject o = access.getHighScores();

        assertEquals(o.get("thomsen"), 69);

        access.postHighScore("thomsen", 22);

        o = access.getHighScores();

        assertEquals(o.get("thomsen"), 69);
    }

    @Test
    public void badGameAccess(){
        access = new DatabaseAccess("not_a_game");

        assertThrows(BadDatabaseAccessException.class, () -> {
            access.getHighScores();
        });
    }

}
