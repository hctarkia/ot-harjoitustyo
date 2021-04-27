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
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        Text text = new Text("Pisteet: 0");
        text.setFont(new Font(20));
        gpLayout.setTop(text);
        gpLayout.setCenter(field);
        AtomicInteger score = new AtomicInteger();
        
        Snake snake = new Snake(WIDTH, HEIGHT);
        
        Scene scene = new Scene(gpLayout);
        
        GraphicsContext fill = field.getGraphicsContext2D();
        
        scene.setOnKeyPressed((event -> {
            if (event.getCode() == KeyCode.LEFT) {
                if (direction != 2) {
                    direction = 0;
                }
            }
            if (event.getCode() == KeyCode.UP) {
                if (direction != 3) {
                    direction = 1;
                }
            }
            if (event.getCode() == KeyCode.RIGHT) {
                if (direction != 0) {
                    direction = 2;
                }
            }
            if (event.getCode() == KeyCode.DOWN) {
                if (direction != 1) {
                    direction = 3;
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
                fill.setFill(Color.WHITE);
                fill.clearRect(0, 0, WIDTH, HEIGHT);
                // snake
                fill.setFill(Color.PINK);
                for (int i = 0; i < snake.getSnake().size(); i++) {
                    fill.fillRect(snake.getSnake().get(i).getX(), snake.getSnake().get(i).getY(), 10, 10);
                }
                // food
                fill.setFill(Color.GREENYELLOW);
                fill.fillOval(snake.getFood().getX(), snake.getFood().getY(), 10, 10);
                
                snake.move(direction);
                
                if(snake.dead()) {
                    stop();
                }
                
                prevTime = currentNanoTime;
            }
        }.start();
        
        stage.setScene(scene);
    }
}
