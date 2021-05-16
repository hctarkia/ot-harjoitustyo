package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.domain.Food;
import snake.domain.Point;
import snake.domain.Snake;

/**
 * Testaa luokkaa Food.
 */

public class FoodTest {
    
    Food food;
    Snake snake;
    
    @Before
    public void setUp() {
        snake = new Snake(10, 10);
        food = new Food(10, 10, snake.getSnake());
    }
    
    @Test
    public void createsFood() {
        assertFalse(food.getFood() == null);
    }
    
    @Test
    public void eatingCreatesNewFood() {
        Point oldFood = food.getFood();
        food.eaten(snake.getSnake());
        assertFalse(food.getFood() == oldFood);
    }
}
