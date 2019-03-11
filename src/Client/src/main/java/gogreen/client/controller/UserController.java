package gogreen.client.controller;

import gogreen.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;



//    @Value("${my.url}")
//    private String myUrl;

//    @FXML
//    private void initialize(ActionEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Scene scene = new Scene(parent);
//        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(scene);
//        app_stage.show();
//    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        Window owner = loginButton.getScene().getWindow();
        if(usernameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Log-in Error!",
                    "Please enter your username");
            return;
        } else {
//            newUser.setUsername(usernameField.getText());
            System.out.println("Username is " + usernameField.getText());
        }
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Log-in Error!",
                    "Please enter a password");
            return;
        } else {
//            newUser.setPassword(passwordField.getText());
            System.out.println("Password is " + passwordField.getText());
        }

        userService.registerUser(usernameField.getText(), passwordField.getText());

    // load the dashboard stage
//        Parent parent = FXMLLoader.load(this.getClass().getClassLoader().getResource("/fxml/dashboard.fxml"));
//
//        Scene scene = new Scene(parent);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(scene);
//        app_stage.setFullScreen(true);
//        app_stage.show();

        //on,y works once, when already logged in once, need to restart client for it to work again
        openDashboard();

    }

    public void openDashboard() throws IOException {
        Parent dash = FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/Dashboard.fxml"));
        Scene scene = new Scene(dash);
        Stage app_stage = new Stage();
        app_stage.setScene(scene);
//        app_stage.setFullScreen(true);
        app_stage.show();
    }

    public static class AlertHelper {
        public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }

}
