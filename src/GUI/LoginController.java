package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LoginController {

    //set labels and textfields, with the @FXML line to let the fxml file know it can use these
    @FXML
    private Label statustext;

    @FXML
    private TextField usertext;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginbutton;

    /**
     * redirects to the main stage when the login credentials are correct
     * @param event the entered username and password, and clicking the button
     * @throws Exception
     * @author Sem van der Hoeven
     */
    public void Login(ActionEvent event) throws Exception {
        //if the entered username and password are correct
        if (usertext.getText().equals("user") && passwordField.getText().equals("pass")) {
            //display green login succes message
            statustext.setText("Login success!");
            statustext.setTextFill(Color.GREEN);
            //and open next window
            openMainWindow();

        } else {
            //else display red login failed message
            statustext.setText("Login failed! try again!");
            statustext.setTextFill(Color.RED);
        }

    }

    public void openMainWindow() throws Exception {
        //basically the same thing as in the main class: open a new stage
        Stage primaryStage = new Stage();
        //link fxml file
        Parent root = FXMLLoader.load(getClass().getResource("GUIMain.fxml"));
        //set the scene
        Scene scene = new Scene(root, 800, 600);
        //link the stylesheet with the scene
        scene.getStylesheets().add(getClass().getResource("GUIStyle.css").toExternalForm());

        //show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("oop project group 43");
        primaryStage.show();
    }
}
