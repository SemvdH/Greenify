package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

@Controller
public class DashBoardController {

    private int count = 0;

    @FXML
    public AnchorPane menuBar;
    public AnchorPane dashboardPane;
    public AnchorPane userPane;
    public AnchorPane activitiesPane;
    public Label welcomebacktext;
    public Button dashboardButton;
    public Button activitiesButton;
    public Button userButton;
    public Button veganMealButton;
    public Label counter;
    public Label scoreField;

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
        UserService service = new UserService();
        service.addVeganMeal(null, null);
        System.out.println("Vegetarian meal is added");
    }
}
