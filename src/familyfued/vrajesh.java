import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javax.swing.text.LabelView;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

public class Main extends Application{

    Stage window;
    Scene home, Scene1, Scene2, Scene3, Scene4;
    Button homeButton, Round1, Round2, Round3, Round4;
    BorderPane[] layouts;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;


        BorderPane[] rounds = createLayouts("sample.json");
        homeButton = new Button("Home");
        homeButton.setOnAction( e -> window.setScene(home));
        Round1 = new Button("Round 1");
        Round2 = new Button("Round 2");
        Round3 = new Button("Round 3");
        Round4 = new Button("Round 4");



        VBox answersLayout = new VBox(10);
        Label A1 = new Label("???");
        Label A2 = new Label("???");
        Label A3 = new Label("???");
        Label A4 = new Label("???");
        Label A5 = new Label("???");
        Label A6 = new Label("???");
        Label A7 = new Label("???");

        A1.setOnMousePressed(e -> A1.setText("Asia"));
        A2.setOnMousePressed(e -> A2.setText("Europe"));
        A3.setOnMousePressed(e -> A3.setText("Africa"));
        A4.setOnMousePressed(e -> A4.setText("Antarctica"));
        A5.setOnMousePressed(e -> A5.setText("Australia"));
        A6.setOnMousePressed(e -> A6.setText("North America"));
        A7.setOnMousePressed(e -> A7.setText("South America"));
        answersLayout.getChildren().addAll(A1,A2,A3,A4,A5,A6,A7);

        BorderPane layout1 = new BorderPane();
        layout1.setTop(new Label("Continents of Earth"));
        layout1.setCenter(answersLayout);
        layout1.setRight(new Label("Hello"));

        Scene roundscene1 = new Scene(layout1, 600, 400);

        Round1.setOnAction(e -> window.setScene(roundscene1));
//        Scene roundscene2 = new Scene(layout1, 600 ,600);
//        Scene roundscene3 = new Scene(layout1, 600 ,600);
//        Scene roundscene4 = new Scene(layout1, 600 ,600);

        VBox homeLayout = new VBox(20);
        homeLayout.getChildren().addAll(homeButton, Round1, Round2, Round3, Round4);
        home = new Scene(homeLayout, 300, 250);


        window.setScene(home);
        window.show();

    }




    public BorderPane[] createLayouts(String filename){
       BorderPane[] layouts;

        BorderPane thisPane = new BorderPane();
        Label questionLabel;
        String currentDir = System.getProperty("user.dir");
        String loc = currentDir + "/src/" + filename;
        JSONParser parser = new JSONParser();
        JSONArray arr = new JSONArray();
        JSONObject obj = new JSONObject();
        String location = "";
        int count = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(loc));
            arr = (JSONArray) parser.parse(reader);
            Iterator<JSONObject> iterator = arr.iterator();
            while(iterator.hasNext()){
                JSONObject thisObject = iterator.next();
                count++;
            }
            layouts = new BorderPane[count]; //count is the number of questions we have..
            int temp = 0;
            iterator = arr.iterator();
            while(iterator.hasNext()){ //same iterator as above , but doing actual work..
//                System.out.println(iterator.next());
                JSONObject thisObject = iterator.next();
                String questionText = (String) thisObject.get("question");
                questionLabel = new Label(questionText); //creating question label here..
                thisPane.setTop(questionLabel);
                JSONArray answersArray = (JSONArray) thisObject.get("answer");
                Iterator<JSONArray> innerIterator = answersArray.iterator();
                while(innerIterator.hasNext()){
                    System.out.println(innerIterator.next());
                }
                layouts[temp] = thisPane; //add layout to layout array;
                System.out.println("Adding a layout");
                temp++;
            }
            return layouts;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
