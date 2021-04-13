package snake.snake;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Snake {
    
    private Rectangle snake;
    
    public Snake(int x, int y) {
        this.snake = new Rectangle(10,10);
        this.snake.setTranslateX(x);
        this.snake.setTranslateY(y);
    }
    
    public boolean eat(Food food) {
        Shape eat = Shape.intersect(this.snake, food.getFood());
        return eat.getBoundsInLocal().getWidth() != -1;
    }
    
    public Rectangle getSnake() {
        return this.snake;
    }
}
