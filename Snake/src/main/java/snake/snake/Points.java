package snake.snake;

public class Points {
    private String name;
    private int points;
    
    public Points(String name, int points) {
        this.name = name;
        this.points = points;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public String toString() {
        return this.name + ";" + this.points;
    }
}
