package greenify.client.controller;

import greenify.client.Application;
import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private PasswordField passwordField;
    private Button loginButton;
    private Button signupButton;

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        Window owner = loginButton.getScene().getWindow();
        if (usernameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Log-in Error!",
                    "Please enter your username");
            return;
        } else {
            System.out.println("Username is " + usernameField.getText());
        }
        if (passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Log-in Error!",
                    "Please enter a password");
            return;
        } else {
            System.out.println("Password is " + passwordField.getText());
        }
        userService.loginUser(usernameField.getText(), passwordField.getText());
        Stage current = (Stage) owner;
        current.close();
        openDashboard();

    }

    /**
     * opens the dashboard stage.
     * @throws IOException exception if fxml file can't be found
     * @author sem
     */
    public void openDashboard() throws IOException {
        Parent dash = Application.load(this.getClass().getClassLoader()
                                        .getResource("fxml/dashboard.fxml"));
        Scene scene = new Scene(dash);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("stylesheets/dashboardStyle.css").toExternalForm());
        Stage appStage = new Stage();
        appStage.setScene(scene);
        appStage.show();
    }

    public static class AlertHelper {
        /**
         * alerts for the login screen.
         * @param alertType the type of alert
         * @param owner the owner (window) of the alert
         * @param title the title given to the displayed alert
         * @param message the message displayed in the alert
         */
        public static void showAlert(Alert.AlertType alertType,
                                     Window owner,
                                     String title,
                                     String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();
        }
    }

    /**
     * The method handles register button.
     * @param event User clicks to the button
     * @throws Exception when the file couldn't find
     */
    public void handleRegisterButtonAction(ActionEvent event) throws Exception {
        Parent registerWindow = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/RegisterWindow.fxml"));
        Scene registerScene = new Scene(registerWindow);
        Stage registerStage = new Stage();
        registerStage.setScene(registerScene);
        registerStage.setTitle("Enter register credentials");
        registerStage.show();
    }

}
