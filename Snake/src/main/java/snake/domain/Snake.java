package snake.domain;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.geometry.Point2D;
import java.util.ArrayList;

public class Snake {
    
    private ArrayList<Rectangle> snake;
    private Point2D movement;
    
    public Snake(int x, int y) {
        this.snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.snake.add(new Rectangle(10, 10));
            this.snake.get(i).setTranslateX(x + i * 10);
            this.snake.get(i).setTranslateY(y);
        }
        this.movement = new Point2D(0.1, 0);
    }
    
    public boolean eat(Food food) {
        Shape eat = Shape.intersect(this.snake.get(0), food.getFood());
        return eat.getBoundsInLocal().getWidth() != -1;
    }
    
    public ArrayList<Rectangle> getSnake() {
        return this.snake;
    }
    
    public void move() {
        for (Rectangle r: this.snake) {
            r.setTranslateX(r.getTranslateX() + this.movement.getX());
            r.setTranslateY(r.getTranslateY() + this.movement.getY());
        }
        
    }
    
    public void turn() {
        
    }
}
