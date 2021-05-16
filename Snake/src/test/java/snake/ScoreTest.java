package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.domain.Score;

/**
 * Testaa luokkaa Score.
 */

public class ScoreTest {
    
    private Score score;
    
    @Before
    public void setUp() {
        score = new Score("Andre", 3000);
    }
    
    @Test
    public void returnsName() {
        assertTrue(score.getName().equals("Andre"));
    }
    
    @Test
    public void returnsPoints() {
        assertTrue(score.getPoints() == 3000);
    }
}
