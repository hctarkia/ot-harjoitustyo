package snake.domain;

public class Point {
    int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }
    
    @Override
    public boolean equals(Object other) {
        Point o = (Point) other;
        if (o == this) {
            return true;
        }
        return this.x == o.x && this.y == o.y;
    }
}
