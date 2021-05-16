package snake.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vastaa madon toteutuksesta.
 */

public class Snake {
    
    private ArrayList<Point> snake;
    private Point head;
    private int width;
    private int height;
    private Random rnd;
    
    /**
     * Luo uuden Snake-olion.
     * @param x Pelikentän leveys.
     * @param y Pelikentän korkeus.
     */
    
    public Snake(int x, int y) {
        snake = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            snake.add(new Point(x / 2 + i * 10, y / 2));
        }
        head = snake.get(0);
        width = x;
        height = y;
    }
    
    /**
     * Tarkistaa osuuko madon pää ruokaan kentällä.
     * @param mouth Madon pään sijainti.
     * @param food Ruoan sijainti.
     * @return Palauttaa true, jos madon pää osuu ruokaan ja false, jos ei.
     */
    
    public boolean eat(Point mouth, Point food) {
        if (mouth.equals(food)) {
            return true;
        }
        return false;
    }
    
    /**
     * Palauttaa listan madon kehon osien sijainneista, että mato saadaan piirrettyä oikeaan paikkaan.
     * @return Palauttaa listan madon kehon osien sijainneista.
     */
    
    public ArrayList<Point> getSnake() {
        return snake;
    }
    
    /**
     * Liikuttaa matoa johonkin suuntaan ja kasvattaa matoa, jos se syö.
     * @param direction Suunta, mihin mato liikkuu.
     * @param eat Syökö mato.
     */
    
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
    
    /**
     * Tarkistaa, kuoleeko mato.
     * @return Palauttaa true, jos kuolee ja false, jos ei kuole.
     */
    
    public boolean dead() {
        head = snake.get(0);
        if (head.getX() < 0 || head.getY() < 0 || head.getX() > width || head.getY() > height) {
            return true;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }
}
