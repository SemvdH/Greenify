package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FriendController {
    @Autowired
    UserService userService;

    @FXML
    private Button addButton;
    @FXML
    private TextField userNameText;

    /**
     * Signs up the user.
     * @param event the click of the sign up button
     */
    @FXML
    public void addFriend(ActionEvent event) throws InterruptedException {
        //set the window to the current window (for displaying the alerts)
        Window owner = addButton.getScene().getWindow();
        //check if the username field is empty
        if (userNameText.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Username Error!",
                    "Please enter a username!");
            return;
        } else if (userNameText.getText().equals(userService.currentUser.getName())) {
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Cannot add yourself as a friend!");
            return;
        } else if (userService.getFriendNames(userService.currentUser.getName())
                .contains(userNameText.getText())) {
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Cannot add a friend twice!");
            return;
        } else if (!userService.getAllUsers().contains(userNameText.getText())) {
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "The user does not exist!");
            return;
        }
        //add friend to the current user
        userService.addFriend(userService.currentUser.getName(), userNameText.getText());
        //close the register window after the user has entered all the credentials
        Stage current = (Stage) owner;
        current.close();
    }
}
