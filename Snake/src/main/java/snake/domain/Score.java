package snake.domain;

/**
 * Luokka liittää nimen ja pisteet yhteen olioon.
 * @author hctarkia
 */

public class Score {
    private String name;
    private int score;
    
    /**
     * Luo uuden Score-olion.
     * @param name Pelaajan nimi.
     * @param score Pelaajan pisteet.
     */
    
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Palauttaa pelaajan nimen.
     * @return Pelaajan nimi.
     */
    
    public String getName() {
        return this.name;
    }

    /**
     * Palauttaa pelaajan pisteet.
     * @return Pelaajan pisteet.
     */
    
    public int getPoints() {
        return this.score;
    }
}

    