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
import java.util.HashMap;

public class SnakeApp extends Application {
    
    public static int WIDTH = 1200;
    public static int HEIGHT = 800;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        BorderPane start = new BorderPane();
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        Button play = new Button("Aloita peli");
        Button highscore = new Button("Sijoitukset");
        play.setFont(Font.font(30));
        highscore.setFont(Font.font(30));
        buttons.getChildren().addAll(play,highscore);
        buttons.setSpacing(20);
        
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
        Pane screen = new Pane();
        Text text = new Text(30,40,"Pisteet: 0");
        text.setFont(new Font(40));
        screen.setPrefSize(WIDTH, HEIGHT);
        screen.getChildren().add(text);
        AtomicInteger pisteet = new AtomicInteger();
        
        Scene scene = new Scene(screen);
        
        HashMap<KeyCode,Boolean> pressed = new HashMap<>();
        scene.setOnKeyPressed(event -> {
            pressed.put(event.getCode(),Boolean.TRUE);
        });
        scene.setOnKeyReleased(event -> {
            pressed.put(event.getCode(),Boolean.FALSE);
        });
        
        stage.setScene(scene);
    }
}
