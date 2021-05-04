package snake.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.concurrent.atomic.AtomicInteger;
import snake.domain.Snake;
import snake.domain.Food;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayDeque;

public class SnakeUi extends Application {
    
    public static int WIDTH = 400;
    public static int HEIGHT = 400;
    public static int direction = 0;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
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
            gameplay(stage,menu);
        });
        
        stage.setTitle("Matopeli");
        stage.setScene(menu);
        stage.show();
    }
    
    public void gameplay(Stage stage, Scene front) {
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
                    Button menu = new Button("Valikkoon");
                    menu.setFont(new Font(30));
                    menu.setOnAction((event -> {
                        stage.setScene(front);
                    }));
                    dead.getChildren().addAll(end, menu);
                    dead.setSpacing(30);
                    gpLayout.setCenter(dead);
                }
                
                prevTime = currentNanoTime;
            }
        }.start();
        
        stage.setScene(scene);
    }
}
