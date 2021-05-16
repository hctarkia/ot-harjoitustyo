package snake;

import java.io.FileInputStream;
import java.util.Properties;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.dao.Highscores;
import snake.domain.Score;

/**
 * Testaa luokkaa Highscores.
 */

public class HighscoresTest {
    
    Highscores highscores;
    
    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String test = properties.getProperty("test");
        highscores = new Highscores(test);
    }
    
    @After
    public void tearDown() {
        highscores.dropTable();
    }
    
    @Test
    public void returnsListOfTenScores() {
        ArrayList<Score> list = new ArrayList<>();
        try {
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3020);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3050);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3070);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            list = highscores.list();
        } catch (SQLException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        assertTrue(list.size() == 10);
    }
    
    @Test
    public void biggestIsFirst() {
        ArrayList<Score> list = new ArrayList<>();
        try {
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3020);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3050);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3070);
            highscores.add("Andre", 3000);
            highscores.add("Andre", 3000);
            list = highscores.list();
        } catch (SQLException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        assertTrue(list.get(0).getPoints() == 3070);
    }
}
