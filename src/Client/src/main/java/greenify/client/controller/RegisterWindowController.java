package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

/**
 * Class that controls the actions for the register window.
 */
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
    private Button signUpButton;
    //@FXML
    //private Line uNamePathLine;

    /**
     * Initializes the animation.
     * @throws InterruptedException exception when interrupted
     */
    public void initialize() throws InterruptedException {
        //        PathTransition pathTransUName = new PathTransition(Duration.millis(1100),
        //        uNamePathLine, userNameText);
        //        pathTransUName.play();
        addSlideAnimation(1100, userNameText, -300);
        addSlideAnimation(1100, passwordField, 300);
        TimeUnit.MILLISECONDS.sleep(300);
        addSlideAnimation(1100, passwordField2, -420);

    }

    /**
     * Adds the slide animation.
     * @param duration the duration
     * @param node the node
     * @param from from where
     */
    private void addSlideAnimation(int duration, Node node, int from) {
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(duration), node);
        slideIn.setFromX(from);
        slideIn.setToX(0);
        slideIn.play();
    }

    /**
     * Signs up the user.
     * @param event the click of the sign up button
     */
    @FXML
    public void handleSignUpButton(ActionEvent event) {
        //set the window to the current window (for displaying the alerts)
        Window owner = signUpButton.getScene().getWindow();
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

        //register the user with the provided username and password
        userService.registerUser(userNameText.getText(), passwordField.getText());

        //close the register window after the user has entered all the credentials
        Stage current = (Stage) owner;
        current.close();
    }
}
