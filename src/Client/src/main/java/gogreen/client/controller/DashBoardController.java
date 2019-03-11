package gogreen.client.controller;

import gogreen.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
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

    public void handleClickAction(MouseEvent event) {
        if (event.getTarget() == dashboardButton) {
            dashboardPane.setVisible(true);
            userPane.setVisible(false);
            activitiesPane.setVisible(false);
        } else if (event.getTarget() == activitiesButton){
            dashboardPane.setVisible(false);
            userPane.setVisible(false);
            activitiesPane.setVisible(true);
        } else if (event.getTarget() == userButton) {
            dashboardPane.setVisible(false);
            userPane.setVisible(true);
            activitiesPane.setVisible(false);
        }
    }

    public void displayDashboard(ActionEvent event) {
        dashboardPane.setVisible(true);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
    }

    public void displayActivities(ActionEvent event) {
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(true);
    }

    public void displayUser(ActionEvent event) {
        dashboardPane.setVisible(false);
        userPane.setVisible(true);
        activitiesPane.setVisible(false);
    }
    public void addShadow(MouseEvent event) {
        userButton.setEffect(shadow);
    }

    public void removeShadow(MouseEvent event) {
        userButton.setEffect(null);

    }

}
