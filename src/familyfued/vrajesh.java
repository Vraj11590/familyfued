import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Familyfued extends Application{


    Button button;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        //stage contains the scene
        primaryStage.setTitle("Welcome to Family Feud");
        button = new Button();
        button.setText("Click to Play!");
        button.setOnAction(e -> {
            System.out.println("Hello!");
            System.out.println("Hello!");
            System.out.println("Hello!");
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);


        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
