import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//MAIN CLIENT

public class Main {
    public static void main(String[] args){

        System.out.println("Hello World!");
    }
}

//COMMENT THE "MAIN CLIENT" PART OUT AND UNCOMMENT THE "JAVAFX" PART TO TEST IF JAVAFX WORKS
//Don't uncomment the imports pls :)

//JAVAFX

//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//
//        Button btn2 = new Button();
//        btn2.setText("Say something else!");
//        btn2.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("something else!");
//            }
//        });
//
//        StackPane root = new StackPane();
//        //apparently root.getchildren.add does the same as document.ready in javascript
//        root.getChildren().add(btn);
//
//        Scene scene = new Scene(root, 500, 250);
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
