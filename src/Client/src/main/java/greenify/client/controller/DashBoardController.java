package greenify.client.controller;

import greenify.client.Application;
import greenify.client.Friend;
import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Class that controls the dashboard fxml file (the GUI Screen).
 */
@Controller
public class DashBoardController {
    @Autowired
    UserService userService;

    private FadeTransition fadeTrans;       //transition for switching between the different panels

    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane userPane;
    @FXML
    private AnchorPane activitiesPane;
    @FXML
    private AnchorPane friendsPane;
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
    @FXML
    private Button calculateFootPrintButton;
    @FXML
    private Label footprintLabel;
    @FXML
    private Button addFriendButton;
    @FXML
    private TableView<Friend> friendsTable;
    @FXML
    private TableColumn<Friend, String> friendsColumn;
    @FXML
    private TableColumn<Friend, Float> scoreColumn;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label usernameLabel;

    /**
     * Loads the the necessary things before anything else.
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
        friendsColumn.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        friendsTable.setItems(FriendController.getData());
        if(pieChart != null) {
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Vegan Meal", 100),
                            new PieChart.Data("Public Transport", 200),
                            new PieChart.Data("Home Temperature", 50),
                            new PieChart.Data("Bike", 75),
                            new PieChart.Data("Local Product", 110),
                            new PieChart.Data("Solar Panel", 300)
                    );
            pieChart.setTitle("FOOTPRINT DISTRIBUTION");
            pieChart.setMaxSize(1000, 1000);
            pieChart.setData(pieChartData);
        }
    }

    /**
     * Adds a fade transition for switching between the different panes.
     * @param node the node on which the transition needs to act
     */
    public void addFadeTransition(Node node) {

        fadeTrans = new FadeTransition(Duration.millis(400), node);
        fadeTrans.setFromValue(0);
        fadeTrans.setToValue(1.0);
        fadeTrans.play();
    }


    /**
     * Displays the dashboard pane.
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
     * Displays the activities pane.
     * @param event the event (clicking the button)
     */
    public void displayActivities(ActionEvent event) {
        addFadeTransition(activitiesPane);
        System.out.println("display activities");
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(true);
        friendsPane.setVisible(false);
    }

    /**
     * Displays the user profile pane.
     * @param event the event (clicking the button)
     */
    public void displayUser(ActionEvent event) {
        System.out.println(userService.currentUser.getName());
        System.out.println(userService.getFootprint(userService.currentUser.getName()));
        footprintLabel.setText("" + userService.getFootprint(userService.currentUser.getName()));
        usernameLabel.setText("" + userService.currentUser.getName());
        addFadeTransition(userPane);
        System.out.println("display user");
        dashboardPane.setVisible(false);
        userPane.setVisible(true);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(false);

    }

    /**
     * Displays the friends pane.
     * @param event the event (clicking the button)
     */
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

    /**
     * Opens the calculator.
     * @throws IOException if the Application doesn't load.
     */
    public void openCalculator() throws IOException {
        Parent calc = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/calculator.fxml"));
        Scene scene = new Scene(calc);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("stylesheets/calculatorStyle.css").toExternalForm());
        Stage calcStage = new Stage();

        calcStage.setScene(scene);
        calcStage.setTitle("Calculate CO2 footprint - " + userService.currentUser.getName());
        calcStage.show();
    }

    public void openAddFriend() throws IOException {
        Parent calc = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/AddFriend.fxml"));
        Scene scene = new Scene(calc);
        Stage calcStage = new Stage();
        calcStage.setScene(scene);
        calcStage.setTitle("Add a new friend - " + userService.currentUser.getName());
        calcStage.show();
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