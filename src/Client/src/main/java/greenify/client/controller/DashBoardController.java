package greenify.client.controller;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//Class that controls the dashboard fxml file (the GUI Screen)


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

    @FXML
    public Button dashboardButton;
    public Button activitiesButton;
    public Button userButton;
    public Line pathLine;
    public AnchorPane menuBar;
    public Button addNewActivityButton;

    FadeTransition fadeTrans;       //transition for switching between the different panels

    /**
     * loads the the necessary things before anything else.
     */
    public void initialize() {
        //sets the text of the 'welcome back' text to include the username
        welcomebacktext.setText("Welcome back, " + userService.currentUser.getName() + "!");
        //adds the slide transition to the menu bar
        addSlideTransition(menuBar, pathLine);
        //adds animations to the navigation buttons
        dashboardButton.setSkin(new MyButtonSkin(dashboardButton));
        activitiesButton.setSkin(new MyButtonSkin(activitiesButton));
        userButton.setSkin(new MyButtonSkin(userButton));



    }

    /**
     * adds a fade transition for switching between the different panes.
     * @param node the node on which the transition needs to act
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

        int net = userService.currentUser.getVeganMeal() + count;
        totalVeganMealCounter.setText("" + net);
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

    //sets the slide in transition for startup
    public void addSlideTransition(Node node, Line path1) {
        PathTransition pathTrans = new PathTransition(Duration.millis(1100), path1, node);
        pathTrans.play();
    }

    //class for the animations on the navigation buttons
    public class MyButtonSkin extends ButtonSkin {
        public MyButtonSkin(Button button) {
            super(button);
            final ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100));
            scaleUp.setNode(button);
            scaleUp.setToX(1.1);
            button.setOnMouseEntered(e -> scaleUp.playFromStart());

            final ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100));
            scaleDown.setNode(button);
            scaleDown.setToX(1.0);
            button.setOnMouseExited(e -> scaleDown.playFromStart());
        }
    }
}