package snake.snake;

import javafx.scene.shape.Circle;
import java.util.Random;

public class Food {
    private Circle food;
    
    public Food() {
        this.food = new Circle(5);
        this.eaten();
    }
    
    public Circle getFood() {
        return this.food;
    }
    
    public void eaten() {
        Random rnd = new Random();
        this.food.setTranslateX(rnd.nextInt(SnakeApp.WIDTH));
        this.food.setTranslateY(rnd.nextInt(SnakeApp.HEIGHT));
    }
}
