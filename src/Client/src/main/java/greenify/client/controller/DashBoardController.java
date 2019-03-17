package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DashBoardController {
    @Autowired
    UserService userService;

    private int count = 0;

    @FXML
    private AnchorPane menuBar;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane userPane;
    @FXML
    private AnchorPane activitiesPane;
    @FXML
    private Label welcomebacktext;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button activitiesButton;
    @FXML
    private Button userButton;
    @FXML
    private Button veganMealButton;
    @FXML
    private Label counter;
    @FXML
    private Label scoreField;

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

    /**
     * adds a vegetarian meal.
     * @param event the event (clicking the button)
     */
    public void addVeganMeal(ActionEvent event) {
        count++;
        counter.setText("Count: " + count);
        System.out.println(userService);
        userService.addVeganMeal(userService.currentUser.getId(),
                                userService.currentUser.getName());
        System.out.println("Vegetarian meal is added");
    }
}
