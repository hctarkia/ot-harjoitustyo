package snake.domain;

import java.util.ArrayList;
import java.util.Random;

public class Snake {
    
    private ArrayList<Point> snake;
    private Point food;
    private Point head;
    private int width;
    private int height;
    private Random rnd;
    
    public Snake(int x, int y) {
        snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(x / 2 + i * 10, y / 2));
        }
        head = snake.get(0);
        width = x;
        height = y;
        rnd = new Random();
        food = new Point(rnd.nextInt(width / 10) * 10, rnd.nextInt(height / 10) * 10);
    }
    
    public boolean eat(Point mouth) {
        if (mouth.equals(food)) {
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
            return true;
        }
        return false;
    }
    
    public ArrayList<Point> getSnake() {
        return snake;
    }
    
    public Point getFood() {
        return food;
    }
    
    public void move(int direction, boolean eat) {
        ArrayList<Point> newSnake = new ArrayList<>();
        head = snake.get(0);
        int x = head.getX();
        int y = head.getY();
        switch (direction) {
            case 0:
                x = head.getX() - 10;
                break;
            case 1:
                y = head.getY() - 10;
                break;
            case 2:
                x = head.getX() + 10;
                break;
            case 3:
                y = head.getY() + 10;
                break;
            default:
                break;
        }
        newSnake.add(new Point(x, y));
        int size = snake.size() - 1;
        if (eat) {
            size = snake.size();
        }
        for (int i = 0; i < size; i++) {
            newSnake.add(snake.get(i));
        }
        snake = newSnake;
    }
    
    public boolean dead() {
        head = snake.get(0);
        if (head.getX() < 0 || head.getY() < 0 || head.getX() > width || head.getY() > height) {
            return true;
        }
        for (int i = 1; i < snake.size(); i++) {
            if(head.equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }
}
