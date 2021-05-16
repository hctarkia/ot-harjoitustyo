package snake.ui;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayDeque;
import java.util.Properties;
import java.io.FileInputStream;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import snake.dao.Highscores;
import snake.domain.Snake;
import snake.domain.Food;
import snake.domain.Score;
import java.sql.SQLException;

/**
 * Luokka toteuttaa sovelluksen käyttöliittymän ja pelin visuaalisen toteutuksen.
 */

public class SnakeUi extends Application {
    private Highscores highscores;
    
    public static int WIDTH = 400;
    public static int HEIGHT = 400;
    public static int direction = 0;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Metodi hakee tietokannan ja luo pistetilaston.
     * @throws Exception 
     */
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String highscore = properties.getProperty("highscores");
        highscores = new Highscores(highscore);
    }
    
    /**
     * Päävalikon toteutus.
     * @param stage
     * @throws SQLException 
     */
    
    @Override
    public void start(Stage stage) throws SQLException {
        BorderPane start = new BorderPane();
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        Text text = new Text("Matopeli");
        text.setFont(new Font(40));
        Button play = new Button("Aloita peli");
        Button highscore = new Button("Sijoitukset");
        play.setFont(Font.font(30));
        highscore.setFont(Font.font(30));
        buttons.getChildren().addAll(text,play,highscore);
        buttons.setSpacing(30);
        
        start.setPrefSize(WIDTH, HEIGHT);
        start.setCenter(buttons);
        
        Scene menu = new Scene(start);
        
        play.setOnAction(event -> {
            try {
                gameplay(stage, menu); 
            } catch (SQLException e) {
                System.out.println("Virhe: " + e.getMessage());
            }
        });
        
        highscore.setOnAction(event -> {
            try {
                showHighscores(stage, menu);
            } catch (SQLException e) {
                System.out.println("Virhe: " + e.getMessage());
            }
        });
        
        stage.setTitle("Matopeli");
        stage.setScene(menu);
        stage.show();
    }
    
    /**
     * Pelin pelaaminen.
     * @param stage Tähän asetetaan Scene-oliot.
     * @param front Päävalikon Scene-olio.
     * @throws SQLException 
     */
    
    public void gameplay(Stage stage, Scene front) throws SQLException {
        BorderPane gpLayout = new BorderPane();
        Canvas field = new Canvas(WIDTH, HEIGHT);
        Group root = new Group();
        root.getChildren().add(field);
        AtomicInteger score = new AtomicInteger();
        Text text = new Text("Pisteet: 0");
        text.setFont(new Font(20));
        gpLayout.setTop(text);
        gpLayout.setCenter(field);
        
        Snake snake = new Snake(WIDTH, HEIGHT);
        Food food = new Food(WIDTH, HEIGHT, snake.getSnake());
        direction = 0;
        
        Scene scene = new Scene(gpLayout);
        
        GraphicsContext fill = field.getGraphicsContext2D();
        
        ArrayDeque<Integer> keys = new ArrayDeque<>();
        scene.setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (direction != 2) {
                    keys.addLast(0);
                }
            }
            if (event.getCode() == KeyCode.UP) {
                if (direction != 3) {
                    keys.addLast(1);
                }
            }
            if (event.getCode() == KeyCode.RIGHT) {
                if (direction != 0) {
                    keys.addLast(2);
                }
            }
            if (event.getCode() == KeyCode.DOWN) {
                if (direction != 1) {
                    keys.addLast(3);
                }
            }
        }));
        
        new AnimationTimer() {
            
            private long sleepNanoseconds = 100000000;
            private long prevTime = 0;
            
            @Override
            public void handle(long currentNanoTime) {
                if ((currentNanoTime - prevTime) < sleepNanoseconds) {
                    return;
                }
                // background
                fill.setFill(Color.BLACK);
                fill.fillRect(0, 0, WIDTH, HEIGHT);
                // snake
                fill.setFill(Color.PINK);
                for (int i = 0; i < snake.getSnake().size(); i++) {
                    fill.fillRect(snake.getSnake().get(i).getX(), snake.getSnake().get(i).getY(), 10, 10);
                }
                // food
                fill.setFill(Color.GREENYELLOW);
                fill.fillOval(food.getFood().getX(), food.getFood().getY(), 10, 10);
                
                if(!keys.isEmpty()) {
                    direction = keys.poll();
                }
                boolean eat = snake.eat(snake.getSnake().get(0), food.getFood());
                snake.move(direction, eat);
                if (eat) {
                    text.setText("Pisteet: " + score.addAndGet(10));
                    food.eaten(snake.getSnake());
                }
                
                
                if(snake.dead()) {
                    stop();
                    VBox dead = new VBox();
                    dead.setAlignment(Pos.CENTER);
                    Text end = new Text("Peli päättyi");
                    end.setFont(new Font(30));
                    HBox addScore = new HBox();
                    addScore.setAlignment(Pos.CENTER);
                    Text name = new Text("Nimi (1-10 kirjanta): ");
                    name.setFont(new Font(20));
                    TextField input = new TextField();
                    input.setPrefColumnCount(10);
                    input.setPrefHeight(20);
                    addScore.getChildren().addAll(name, input);
                    Button add = new Button("Lisää pisteet");
                    add.setFont(new Font(20));
                    add.setOnAction((event -> {
                        String playerName = input.getText();
                        if(playerName.length() > 0 && playerName.length() <= 10) {
                            try {
                                highscores.add(playerName, score.intValue());
                            } catch (SQLException e) {
                                System.out.println("Virhe: " + e.getMessage());
                            }
                            stage.setScene(front);
                        } else {
                            name.setText("Anna nimi (1-10 kirjainta)");
                        }
                    }));
                    Button menu = new Button("Valikkoon");
                    menu.setFont(new Font(20));
                    menu.setOnAction((event -> {
                        stage.setScene(front);
                    }));
                    dead.getChildren().addAll(end, addScore, add, menu);
                    dead.setSpacing(30);
                    gpLayout.setCenter(dead);
                }
                
                prevTime = currentNanoTime;
            }
        }.start();
        
        stage.setScene(scene);
    }
    
    /**
     * Metodi toteuttaa sijoitusten näkymän.
     * @param stage Tähän asetetaan Scene-oliot.
     * @param front Päävalikon Scene-olio.
     * @throws SQLException 
     */
    
    public void showHighscores(Stage stage, Scene front) throws SQLException {
        BorderPane base = new BorderPane();
        base.setPrefSize(WIDTH, HEIGHT);
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        Text header = new Text("Sijoitukset");
        header.setFont(new Font(25));
        header.setTextAlignment(TextAlignment.CENTER);
        VBox names = new VBox();
        VBox scores = new VBox();
        HBox namesAndScores = new HBox();
        namesAndScores.setAlignment(Pos.CENTER);
        namesAndScores.setSpacing(100);
        for (Score score: highscores.list()) {
            Text name = new Text(score.getName());
            name.setFont(new Font(20));
            names.getChildren().add(name);
            Text points = new Text(Integer.toString(score.getPoints()));
            points.setFont(new Font(20));
            scores.getChildren().add(points);
        }
        namesAndScores.getChildren().addAll(names, scores);
        Button menu = new Button("Takaisin valikkoon");
        menu.setFont(new Font(20));
        menu.setAlignment(Pos.CENTER);
        menu.setOnAction(event -> {
            stage.setScene(front);
        });
        layout.getChildren().addAll(header, namesAndScores, menu);
        base.setCenter(layout);
        Scene scene = new Scene(base);
        stage.setScene(scene);
    }
}
