package snake.domain;

/**
 * Luokka määrittelee koordinaatit pelikentällä.
 */

public class Point {
    int x, y;
    
    /**
     * Luo uuden Point-olion.
     * @param x Koordinaatti x.
     * @param y Koordinaatti y.
     */
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Palauttaa koordinaatin x.
     * @return Palauttaa koordinaatin x.
     */
    
    public int getX() {
        return this.x;
    }
    
    /**
     * Palauttaa koordinaatin y.
     * @return Palauttaa koordinaatin y.
     */
    
    public int getY() {
        return this.y;
    }
    
    /**
     * Laskee hajautusarvon oliolle.
     * @return Palauttaa olion hajautusarvon.
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }
    
    /**
     * Tarkastaa, onko tämän olion koordinaatit samat verrattavaan olioon.
     * @param other Verrattava olio.
     * @return Palauttaa true, jos koordinaatit ovat samat ja muussa tapauksessa false.
     */
    
    @Override
    public boolean equals(Object other) {
        Point o = (Point) other;
        if (o == this) {
            return true;
        }
        return this.x == o.x && this.y == o.y;
    }
}
