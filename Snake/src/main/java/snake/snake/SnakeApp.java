package snake.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.util.concurrent.atomic.AtomicInteger;

public class SnakeApp extends Application {
    
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        BorderPane start = new BorderPane();
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        Text text = new Text("Matopeli");
        text.setFont(new Font(50));
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
        Pane gpScreen = new Pane();
        Text text = new Text("Pisteet: 0");
        text.setFont(new Font(20));
        gpScreen.setPrefSize(WIDTH, HEIGHT-20);
        gpLayout.setTop(text);
        gpLayout.setCenter(gpScreen);
        AtomicInteger score = new AtomicInteger();
        
        Snake snake = new Snake(WIDTH/2, HEIGHT/2-10);
        gpScreen.getChildren().add(snake.getSnake());
        Food food = new Food();
        gpScreen.getChildren().add(food.getFood());
        
        Scene scene = new Scene(gpLayout);
        
        stage.setScene(scene);
    }
}
