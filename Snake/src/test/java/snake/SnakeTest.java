package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.domain.Snake;

public class SnakeTest {
    
    Snake snake;
    
    @Before
    public void setUp() {
        snake = new Snake(10, 10);
    }
    
    @Test
    public void createsCorrectSizedSnake() {
        assertTrue(snake.getSnake().size() == 3);
    }
    
    @Test
    public void createsFood() {
        assertFalse(snake.getFood() == null);
    }
}
