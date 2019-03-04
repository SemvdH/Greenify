package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIMain extends Application {

    /**
     * launches the stage
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * @param primaryStage the stage to be started
     * @throws Exception when the fxml file can't be found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //link fxml file
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //set the scene
        Scene scene = new Scene(root, 400, 400);
        //link the stylesheet with the scene
        scene.getStylesheets().add(getClass().getResource("LoginStyle.css").toExternalForm());

        //show the stagw
        primaryStage.setScene(scene);
        primaryStage.setTitle("login");
        primaryStage.show();
    }
}
