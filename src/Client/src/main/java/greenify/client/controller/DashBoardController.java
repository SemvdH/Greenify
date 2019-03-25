package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.skin.ButtonSkin;
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

    private FadeTransition fadeTrans;       //transition for switching between the different panels
    private int net;
    private int count = 0;

    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane userPane;
    @FXML
    private AnchorPane activitiesPane;
    @FXML
    private AnchorPane friendsPane;
    @FXML
    private Label veganMealCounter;
    @FXML
    private Label totalVeganMealCounter;
    @FXML
    private Label welcomebacktext;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button activitiesButton;
    @FXML
    private Button userButton;
    @FXML
    private Button friendsButton;
    @FXML
    private Line pathLine;
    @FXML
    private AnchorPane menuBar;
    @FXML
    private Button addNewActivityButton;



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
        friendsButton.setSkin(new MyButtonSkin(friendsButton));
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
        friendsPane.setVisible(false);

    }

    /**
     * displays the activities pane.
     * @param event the event (clicking the button)
     */
    public void displayActivities(ActionEvent event) {
        addFadeTransition(activitiesPane);
        totalVeganMealCounter.setText("" + net);
        System.out.println("display activities");
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(true);
        friendsPane.setVisible(false);
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
        friendsPane.setVisible(false);

    }

    public void displayFriends(ActionEvent event) {
        addFadeTransition(friendsPane);
        System.out.println("display friends");
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(true);

    }

    //sets the slide in transition for startup
    public void addSlideTransition(Node node, Line path1) {
        PathTransition pathTrans = new PathTransition(Duration.millis(1100), path1, node);
        pathTrans.play();
    }

    //class for the animations on the navigation buttons
    public class MyButtonSkin extends ButtonSkin {
        /**
         * adds a skin and scale animation to a button.
         * the scale transition is for hovering over it so it then scales up
         * and scales down when you stop hovering over it.
         * @param button the button to add the animation to
         */
        public MyButtonSkin(Button button) {
            //inherit the button properties
            super(button);
            //transition to scale up on hover
            final ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100));
            //add the node and the position to scale to
            scaleUp.setNode(button);
            scaleUp.setToX(1.1);
            //play the transition when hovered over the button
            button.setOnMouseEntered(e -> scaleUp.playFromStart());

            final ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100));
            scaleDown.setNode(button);
            scaleDown.setToX(1.0);
            button.setOnMouseExited(e -> scaleDown.playFromStart());
        }
    }
}
