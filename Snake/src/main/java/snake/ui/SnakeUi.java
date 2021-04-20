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
import javafx.scene.layout.Pane;
import snake.domain.Food;
import snake.domain.Snake;
import javafx.scene.shape.Rectangle;

public class SnakeUi extends Application {
    
    public static int WIDTH = 400;
    public static int HEIGHT = 400;
    
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
        Pane field = new Pane();
        field.setPrefSize(WIDTH, HEIGHT);
        Text text = new Text("Pisteet: 0");
        text.setFont(new Font(20));
        gpLayout.setTop(text);
        gpLayout.setCenter(field);
        AtomicInteger score = new AtomicInteger();
        
        Snake snake = new Snake(WIDTH/2, HEIGHT/2);
        for(int i = 0; i < snake.getSnake().size(); i++) {
            field.getChildren().add(snake.getSnake().get(i));
        }
        Food food = new Food();
        field.getChildren().add(food.getFood());
        
        Scene scene = new Scene(gpLayout);
        
        new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                snake.move();
            }
        }.start();
        
        stage.setScene(scene);
    }
}
