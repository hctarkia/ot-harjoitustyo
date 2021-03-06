package snake.dao;

import java.sql.*;
import java.util.ArrayList;
import snake.domain.Score;

/**
 * Luokka vastaa pisteiden tallennuksesta tietokantaan.
 */

public class Highscores {
    
    private Connection db;
    
    /**
     * Luo uuden taulun tietokantaan, jos sitä ei ole olemassa ja luo siihen yhteyden.
     * @param data Tietokantatiedoston nimi.
     * @throws SQLException 
     */
    
    public Highscores(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS highscores \n" + 
                    "(id INTEGER PRIMARY KEY, name TEXT, score INTEGER)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
    
    /**
     * Lisää pelaajan pisteet tietokantaan.
     * @param name Pelaajan nimi.
     * @param score Pelaajan pisteet.
     * @throws SQLException 
     */
    
    public void add(String name, int score) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO highscores (name, score) VALUES (?, ?)");
        stmt.setString(1, name);
        stmt.setInt(2, score);
        stmt.executeUpdate();
        stmt.close();
    }
    
    /**
     * Palauttaa listan top 10 suorituksista.
     * @return Palauttaa listan top 10 suorituksista.
     * @throws SQLException 
     */
    
    public ArrayList<Score> list() throws SQLException {
        ArrayList<Score> scores = new ArrayList<>();
        PreparedStatement stmt = db.prepareStatement("SELECT name, score FROM highscores ORDER BY score DESC LIMIT 10");
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String name = rs.getString("name");
            int score = rs.getInt("score");
            scores.add(new Score(name, score));
        }
        
        stmt.close();
        rs.close();
        
        return scores;
    }
    
    /**
     * Metodi poistaa tietokantataulun. Metodia käytetään testien yhteydessä.
     */
    
    public void dropTable() {
        try {
            PreparedStatement stmt = db.prepareStatement("DROP TABLE highscores");
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
}
