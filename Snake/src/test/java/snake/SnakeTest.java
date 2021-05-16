package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.domain.Snake;
import snake.domain.Point;

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
    public void eatingReturnsTrue() {
        assertTrue(snake.eat(new Point(5, 5), new Point(5, 5)));
    }
    
    @Test
    public void notEatingReturnsFalse() {
        assertFalse(snake.eat(new Point(5, 5), new Point(5, 6)));
    }
    
    @Test
    public void eatingGrowsSnake() {
        snake.move(0, snake.eat(new Point(5, 5), new Point(5, 5)));
        assertTrue(snake.getSnake().size() == 4);
    }
    
    @Test
    public void diesWhenOutOfTheFieldLeft() {
        snake.move(0, false);
        snake.move(0, false);
        snake.move(0, false);
        snake.move(0, false);
        snake.move(0, false);
        assertTrue(snake.dead());
    }
    
    @Test
    public void diesWhenOutOfTheFieldRight() {
        snake.move(2, false);
        snake.move(2, false);
        snake.move(2, false);
        snake.move(2, false);
        snake.move(2, false);
        assertTrue(snake.dead());
    }
    
    @Test
    public void diesWhenOutOfTheFieldUp() {
        snake.move(1, false);
        snake.move(1, false);
        snake.move(1, false);
        snake.move(1, false);
        snake.move(1, false);
        assertTrue(snake.dead());
    }
    
    @Test
    public void diesWhenOutOfTheFieldDown() {
        snake.move(3, false);
        snake.move(3, false);
        snake.move(3, false);
        snake.move(3, false);
        snake.move(3, false);
        assertTrue(snake.dead());
    }
    
    @Test
    public void diesWhenEatsTail() {
        snake.move(0, true);
        snake.move(0, true);
        snake.move(1, true);
        snake.move(2, true);
        snake.move(3, true);
        assertTrue(snake.dead());
    }
    
    @Test
    public void snakeIsAlive() {
        assertFalse(snake.dead());
    }
}
