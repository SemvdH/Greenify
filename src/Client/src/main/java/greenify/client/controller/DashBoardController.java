package greenify.client.controller;

import com.sun.javafx.scene.control.skin.ButtonSkin;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class that controls the dashboard fxml file (the GUI Screen).
 */
@Controller
public class DashBoardController {
    public static ObservableList<Friend> data = FXCollections.observableArrayList();
    public ObservableList<Friend> friendLeaderData = FXCollections.observableArrayList();
    public ObservableList<Friend> globalLeaderData = FXCollections.observableArrayList();

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
    private TableView<Friend> globalLeaderboard;
    @FXML
    private TableColumn<Friend, String> globalUser;
    @FXML
    private TableColumn<Friend, Float> globalScore;
    @FXML
    private TableView<Friend> friendLeaderboard;
    @FXML
    private TableColumn<Friend, String> friendUser;
    @FXML
    private TableColumn<Friend, Float> friendScore;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField peopleNumber;
    @FXML
    private TextField income;
    @FXML
    private TextField electricityUsage;
    @FXML
    private TextField cleanEnergy;
    @FXML
    private TextField naturalGasUsage;
    @FXML
    private TextField heatingOilUsage;
    @FXML
    private TextField waterUsage;
    @FXML
    private TextField livingSpace;
    @FXML
    private TextField gasolineMiles;
    @FXML
    private TextField gasolineMpg;
    @FXML
    private TextField dieselMiles;
    @FXML
    private TextField dieselMpg;
    @FXML
    private TextField electricMiles;
    @FXML
    private TextField electricMpg;
    @FXML
    private TextField publicTransportation;
    @FXML
    private TextField airPlane;
    @FXML
    private TextField goodShopping;
    @FXML
    private TextField serviceShopping;
    @FXML
    private TextField meat;
    @FXML
    private TextField grains;
    @FXML
    private TextField dairy;
    @FXML
    private TextField fruits;
    @FXML
    private TextField snacks;
    @FXML
    public CheckBox localProduce;
    @FXML
    public CheckBox loweringTemp;
    @FXML
    public CheckBox bike;
    @FXML
    public CheckBox solarPanels;

    /**
     * Loads the the necessary things before anything else.
     */
    public void initialize() throws InterruptedException {
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
        globalUser.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        globalScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        friendUser.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        friendScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        if (pieChart != null) {
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
        List<String> friendList = userService.getFriendNames(userService.currentUser.getName());
        for (int i = 0; i < friendList.size(); i++) {
            Friend friend = new Friend(friendList.get(i),
                    userService.getFootprint(friendList.get(i)));
            data.add(friend);
        }
        friendsTable.setItems(data);
        updateLeaderboard();
    }

    /**
     * Sorts the scores of users.
     * @param users the list of users
     */
    public void sortScores(List<String> users) throws InterruptedException {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                Float firstScore = userService.getFootprint(users.get(i));
                Float secondScore = userService.getFootprint(users.get(j));
                if (i > j && firstScore < secondScore) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
                if (i < j && firstScore > secondScore) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
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
    public void displayDashboard(ActionEvent event) throws InterruptedException {
        addFadeTransition(dashboardPane);
        System.out.println("display dashboard");
        dashboardPane.setVisible(true);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(false);
        updateLeaderboard();
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
        Map<String, String> inputMap = userService.getInputs(userService.currentUser.getName());
        peopleNumber.setText(inputMap.get("input_size"));
        income.setText(inputMap.get("input_income"));
        electricityUsage.setText(inputMap.get("input_footprint_housing_electricity_dollars"));
        cleanEnergy.setText(inputMap.get("input_footprint_housing_gco2_per_kwh"));
        naturalGasUsage.setText(inputMap.get("input_footprint_housing_naturalgas_dollars"));
        heatingOilUsage.setText(inputMap.get("input_footprint_housing_heatingoil_dollars"));
        waterUsage.setText(inputMap.get("input_footprint_housing_watersewage"));
        livingSpace.setText(inputMap.get("input_footprint_housing_squarefeet"));
        gasolineMiles.setText(inputMap.get("input_footprint_transportation_miles1"));
        gasolineMpg.setText(inputMap.get("input_footprint_transportation_mpg1"));
        dieselMiles.setText(inputMap.get("input_footprint_transportation_miles2"));
        dieselMpg.setText(inputMap.get("input_footprint_transportation_mpg2"));
        electricMiles.setText(inputMap.get("input_footprint_transportation_miles3"));
        electricMpg.setText(inputMap.get("input_footprint_transportation_mpg3"));
        publicTransportation.setText(inputMap.get("input_footprint_transportation_publictrans"));
        airPlane.setText(inputMap.get("input_footprint_transportation_airtotal"));
        goodShopping.setText(inputMap.get("input_footprint_shopping_goods_total"));
        serviceShopping.setText(inputMap.get("input_footprint_shopping_services_total"));
        meat.setText(inputMap.get("input_footprint_shopping_food_meatfisheggs"));
        grains.setText(inputMap.get("input_footprint_shopping_food_cereals"));
        dairy.setText(inputMap.get("input_footprint_shopping_food_dairy"));
        fruits.setText(inputMap.get("input_footprint_shopping_food_fruitvegetables"));
        snacks.setText(inputMap.get("input_footprint_shopping_food_otherfood"));
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
    public void openCalculator() throws IOException, InterruptedException {
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

    /**
     * method opend addFriend scene.
     * @throws IOException when file is not found
     */
    public void openAddFriend() throws IOException {
        Parent calc = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/AddFriend.fxml"));
        Scene scene = new Scene(calc);
        Stage calcStage = new Stage();
        calcStage.setScene(scene);
        calcStage.setTitle("Add a new friend - " + userService.currentUser.getName());
        calcStage.show();
    }

    /**
     * Leaderboard is updaating.
     * @throws InterruptedException throws exception
     */
    public void updateLeaderboard() throws InterruptedException {
        friendLeaderboard.getItems().clear();
        globalLeaderboard.getItems().clear();
        //global leaderboard
        globalLeaderData.removeAll();
        List<String> userList = userService.getAllUsers();
        sortScores(userList);
        for (int j = 0; j < userList.size(); j++) {
            Friend user = new Friend(userList.get(j), userService.getFootprint(userList.get(j)));
            globalLeaderData.add(user);
        }
        globalLeaderboard.setItems(globalLeaderData);
        // friend leaderboard
        friendLeaderData.removeAll();
        String name = userService.currentUser.getName();
        List<String> friendList = userService.getFriendNames(name);
        friendList.add(name);
        sortScores(friendList);
        for (int i = 0; i < friendList.size(); i++) {
            Friend friend = new Friend(friendList.get(i),
                    userService.getFootprint(friendList.get(i)));
            friendLeaderData.add(friend);
        }
        friendLeaderboard.setItems(friendLeaderData);
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