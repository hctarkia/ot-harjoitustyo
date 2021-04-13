package snake;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.snake.Food;

public class FoodTest {
    
    Food food;
    
    @Before
    public void setUp() {
        food = new Food();
    }
    
    @Test
    public void createsCorrectSizedFood() {
        assertTrue(food.getFood().getRadius()==5);
    }
}
