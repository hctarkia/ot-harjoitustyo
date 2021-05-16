package snake.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka sisältää ruoan toteutuksen.
 */

public class Food {
    
    private Point food;
    private int width, height;
    private Random rnd;
    
    /**
     * Luo ruokaa.
     * @param width Pelikentän leveys.
     * @param height Pelikentän korkeus.
     * @param snake Madon osien sijainti pelikentällä listana.
     */
    
    public Food(int width, int height, ArrayList<Point> snake) {
        this.width = width;
        this.height = height;
        rnd = new Random();
        eaten(snake);
    }
    
    /**
     * Palauttaa ruoan sijainnin.
     * @return Palauttaa ruoan sijainnin.
     */
    
    public Point getFood() {
        return food;
    }
    
    /**
     * Sijoittaa uuden ruoan vapaalle paikalle pelikentällä, kun edellinen ruoka on syöty.
     * @param snake Madon osien sijainti pelikentällä listana.
     */
    
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
