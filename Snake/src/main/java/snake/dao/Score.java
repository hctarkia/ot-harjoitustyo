package snake.dao;

public class Score {
    private String name;
    private int score;
    
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPoints() {
        return this.score;
    }
    
    public String toString() {
        return this.name + ":" + this.score;
    }
}
