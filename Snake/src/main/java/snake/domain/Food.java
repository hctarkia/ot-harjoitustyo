package snake.domain;

import java.util.ArrayList;
import java.util.Random;

public class Food {
    
    private Point food;
    private int width, height;
    private Random rnd;
    
    public Food(int width, int height, ArrayList<Point> snake) {
        this.width = width;
        this.height = height;
        rnd = new Random();
        eaten(snake);
    }
    
    public Point getFood() {
        return food;
    }
    
    public void eaten(ArrayList<Point> snake) {
        while (true) {
            food = new Point(rnd.nextInt(width / 10) * 10, rnd.nextInt(height / 10) * 10);
            boolean f = true;
            for (Point s: snake) {
                if (s.equals(food)) {
                    f = false;
                    break;
                }
            }
            if (f) {
                break;
            }
        }
    }
}
