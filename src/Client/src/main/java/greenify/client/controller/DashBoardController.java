package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

public class DashBoardController {
    @Autowired
    UserService userService;

    @FXML
    public AnchorPane menuBar;
    public AnchorPane dashboardPane;
    public AnchorPane userPane;
    public AnchorPane activitiesPane;
    public Label welcomebacktext;
    //    public Button addActivityButton;
    //    public ComboBox addActivity;

    @FXML
    public Label dashboardText;
    public Label activitiesText;
    public Label userText;
    public Button dashboardButton;
    public Button activitiesButton;
    public Button userButton;

    DropShadow shadow = new DropShadow();

    /**
     * displays the dashboard pane.
     * @param event the event (clicking the button)
     */
    public void displayDashboard(ActionEvent event) {
        System.out.println("display dashboard");
        dashboardPane.setVisible(true);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
    }

    /**
     * displays the activities pane.
     * @param event the event (clicking the button)
     */
    public void displayActivities(ActionEvent event) {
        System.out.println("display activities");
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(true);
    }

    /**
     * displays the user profile pane.
     * @param event the event (clicking the button)
     */
    public void displayUser(ActionEvent event) {
        System.out.println("display user");
        dashboardPane.setVisible(false);
        userPane.setVisible(true);
        activitiesPane.setVisible(false);
    }
    //    public void addShadow(MouseEvent event) {
    //        userButton.setEffect(shadow);
    //    }
    //
    //    public void removeShadow(MouseEvent event) {
    //        userButton.setEffect(null);
    //
    //    }

}
