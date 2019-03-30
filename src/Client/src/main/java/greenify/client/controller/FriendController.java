package greenify.client.controller;

import greenify.client.Friend;
import greenify.client.rest.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FriendController {
    @Autowired
    UserService userService;

    public static ObservableList<Friend> data = FXCollections.observableArrayList();

    @FXML
    private Button addButton;
    @FXML
    private TextField userNameText;

    /**
     * Signs up the user.
     * @param event the click of the sign up button
     */
    @FXML
    public void addFriend(ActionEvent event) {
        //set the window to the current window (for displaying the alerts)
        Window owner = addButton.getScene().getWindow();
        //check if the username field is empty
        if (userNameText.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Username Error!",
                    "Please enter a username!");
            return;
        }
        //add friend to the current user
        userService.addFriend(userService.currentUser.getName(), userNameText.getText());
        Friend friend = new Friend(userNameText.getText(),
                userService.getFootprint(userNameText.getText()));
        data.add(friend);
        //close the register window after the user has entered all the credentials
        Stage current = (Stage) owner;
        current.close();
    }

    public static ObservableList<Friend> getData() {
        return data;
    }

}
