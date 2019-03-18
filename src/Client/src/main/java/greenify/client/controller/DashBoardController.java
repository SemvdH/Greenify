package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DashBoardController {
    @Autowired
    UserService userService;

    private int count = 0;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private AnchorPane userPane;

    @FXML
    private AnchorPane activitiesPane;

    @FXML
    private Label veganMealCounter;

    @FXML
    private Label totalVeganMealCounter;

    @FXML
    private Label welcomebacktext;

    private FadeTransition fadeTrans;

    public void initialize() {
        welcomebacktext.setText("Welcome back, " + userService.currentUser.getName() + "!");
    }

    /**
     * Add transition between scenes.
     * @param node for method
     */
    public void addFadeTransition(Node node) {

        fadeTrans = new FadeTransition(Duration.millis(400), node);
        fadeTrans.setFromValue(0);
        fadeTrans.setToValue(1.0);
        fadeTrans.play();
    }

    /**
     * displays the dashboard pane.
     * @param event the event (clicking the button)
     */
    public void displayDashboard(ActionEvent event) {
        addFadeTransition(dashboardPane);
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
        addFadeTransition(activitiesPane);
        totalVeganMealCounter.setText("" + userService.currentUser.getVeganMeal());
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
        addFadeTransition(userPane);
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
        int net = userService.currentUser.getVeganMeal() + count;
        totalVeganMealCounter.setText("" + net);
        veganMealCounter.setText("" + count);
        System.out.println(userService);
        userService.addVeganMeal(userService.currentUser.getId(),
                userService.currentUser.getName());
        System.out.println("Vegetarian meal is added");
    }
}
