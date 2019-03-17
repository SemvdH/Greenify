package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterWindowController {

    @Autowired
    UserService userService;

    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private Button signupButton;

    /**
     * signs the user up.
     * @param event the click of the signup button
     */
    @FXML
    public void handleSignUpButton(ActionEvent event) {
        //set the window to the current window (for displaying the alerts)
        Window owner = signupButton.getScene().getWindow();
        //check if the username field is empty
        if (userNameText.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Username Error!",
                    "Please enter a username!");
            return;
        }

        //check if the password field is empty
        if (passwordField.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                    "Please enter a password!");
            return;
        }

        //check if the two password fields are equal
        if (!passwordField.getText().equals(passwordField2.getText())) {
            //if not, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                    "Please make sure the passwords entered are the same!");
            return;
        }

        userService.registerUser(userNameText.getText(), passwordField.getText());

        //close the register window after the user has entered all the credentials
        Stage current = (Stage) owner;
        current.close();
    }
}
