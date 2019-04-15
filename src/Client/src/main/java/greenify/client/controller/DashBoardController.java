package greenify.client.controller;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import greenify.client.Application;
import greenify.client.Friend;
import greenify.client.Hints;
import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class that controls the dashboard fxml file (the GUI Screen).
 */
@Controller
public class DashBoardController {
    public static ObservableList<Friend> data = FXCollections.observableArrayList();
    public ObservableList<Friend> friendLeaderData = FXCollections.observableArrayList();
    public ObservableList<Friend> globalLeaderData = FXCollections.observableArrayList();
    public ObservableList<Friend> developmentData = FXCollections.observableArrayList();

    @Autowired
    UserService userService;

    Hints hints = new Hints();

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
    private Button logOutButton;
    @FXML
    private Line pathLine;
    @FXML
    private AnchorPane menuBar;
    @FXML
    private Button calculateFootPrintButton;
    @FXML
    private Label footprintLabel;
    @FXML
    private Label firstFootprintLabel;
    @FXML
    private Label differenceLabel;
    @FXML
    private Button addFriendButton;
    @FXML
    private Button addFriend;
    @FXML
    private Button removeFriend;
    @FXML
    private Button addExtraActivityButton;
    @FXML
    private Button addExtraActivityButton2;
    @FXML
    private TableView<Friend> friendsTable;
    @FXML
    private TableColumn<Friend, String> friendsColumn;
    @FXML
    private TableColumn<Friend, Float> scoreColumn;
    @FXML
    private TableView<Friend> globalLeaderboard;
    @FXML
    private TableColumn<Friend, Integer> globalPlace;
    @FXML
    private TableColumn<Friend, String> globalUser;
    @FXML
    private TableColumn<Friend, Float> globalScore;
    @FXML
    private TableView<Friend> developmentLeaderboard;
    @FXML
    private TableColumn<Friend, Integer> developmentPlace;
    @FXML
    private TableColumn<Friend, String> developmentUser;
    @FXML
    private TableColumn<Friend, Float> developmentScore;
    @FXML
    private TableView<Friend> friendLeaderboard;
    @FXML
    private TableColumn<Friend, Integer> friendPlace;
    @FXML
    private TableColumn<Friend, String> friendUser;
    @FXML
    private TableColumn<Friend, Float> friendScore;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label peopleNumber;
    @FXML
    private Label income;
    @FXML
    private Label electricityUsage;
    @FXML
    private Label cleanEnergy;
    @FXML
    private Label naturalGasUsage;
    @FXML
    private Label heatingOilUsage;
    @FXML
    private Label waterUsage;
    @FXML
    private Label livingSpace;
    @FXML
    private Label gasolineMiles;
    @FXML
    private Label gasolineMpg;
    @FXML
    private Label dieselMiles;
    @FXML
    private Label dieselMpg;
    @FXML
    private Label electricMiles;
    @FXML
    private Label electricMpg;
    @FXML
    private Label publicTransportation;
    @FXML
    private Label airPlane;
    @FXML
    private Label goodShopping;
    @FXML
    private Label serviceShopping;
    @FXML
    private Label meat;
    @FXML
    private Label grains;
    @FXML
    private Label dairy;
    @FXML
    private Label fruits;
    @FXML
    private Label snacks;
    @FXML
    private ImageView achieve1;
    @FXML
    private ImageView achieve2;
    @FXML
    private ImageView achieve3;
    @FXML
    private ImageView achieve4;
    @FXML
    private ImageView achieve5;
    @FXML
    private ImageView achieve6;
    @FXML
    private Label hintText;
    @FXML
    private Label veganMeal;
    @FXML
    private Label localProduce;
    @FXML
    private Label bike;
    @FXML
    private Label publicTrans;
    @FXML
    private Label loweringTemp;
    @FXML
    private Label solarPanels;
    @FXML
    private Button achiev1Tip;
    @FXML
    private Button achiev2Tip;
    @FXML
    private Button achiev3Tip;
    @FXML
    private Button achiev4Tip;
    @FXML
    private Button achiev5Tip;
    @FXML
    private Button achiev6Tip;

    /**
     * Loads the the necessary things before anything else.
     * @throws InterruptedException exception if interrupted
     */
    public void initialize() throws InterruptedException {


        hintText.setWrapText(true);
        hintText.setText(hints.randomHint());
        //set the dashboardPane to visible
        dashboardPane.setVisible(true);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(false);
        //sets the text of the 'welcome back' text to include the username
        welcomebacktext.setText("Welcome back, " + userService.currentUser.getName() + "!");
        //adds the slide transition to the menu bar
        addSlideTransition(menuBar, pathLine);
        //adds animations to the navigation buttons
        dashboardButton.setSkin(new MyButtonSkin(dashboardButton));
        activitiesButton.setSkin(new MyButtonSkin(activitiesButton));
        userButton.setSkin(new MyButtonSkin(userButton));
        friendsButton.setSkin(new MyButtonSkin(friendsButton));
        logOutButton.setSkin(new MyButtonSkin(logOutButton));
        friendsColumn.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        globalPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
        globalUser.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        globalScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        developmentUser.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        developmentPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
        developmentScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        friendPlace.setCellValueFactory(new PropertyValueFactory<>("Place"));
        friendUser.setCellValueFactory(new PropertyValueFactory<>("Friend"));
        friendScore.setCellValueFactory(new PropertyValueFactory<>("Score"));
        List<String> friendList = userService.getFriendNames(userService.currentUser.getName());
        for (int i = 0; i < friendList.size(); i++) {
            Friend friend = new Friend(i, friendList.get(i),
                    userService.getFootprint(friendList.get(i)));
            data.add(friend);
        }
        friendsTable.setItems(data);
        updateLeaderboard();
        updateAchievements();
        updatePiechart();
        calculateFootPrintButton.setSkin(new ClickButtonSkin(calculateFootPrintButton));
        addFriendButton.setSkin(new ClickButtonSkin(addFriendButton));
        addExtraActivityButton.setSkin(new ClickButtonSkin(addExtraActivityButton));
        addExtraActivityButton2.setSkin(new ClickButtonSkin(addExtraActivityButton2));
        addRandomHints();

        Tooltip tooltip = new Tooltip("tip");
        hackTooltipStartTiming(tooltip);

        addToolTip(achiev1Tip, "Starting off \n You did your first green activity!");
        addToolTip(achiev2Tip, "Social Butterfly \n You added three friends");
        addToolTip(achiev3Tip, "Green Saver \n You saved 20 tonnes of CO2");
        addToolTip(achiev4Tip, "Animal Friend \n You have eaten 10 vegetarian meals");
        addToolTip(achiev5Tip, "Tom Dumoulin \n You have biked 15 km");
        addToolTip(achiev6Tip, "Let it shine \n You installed solar panels");
    }

    /**
     * adds a tooltip to the button.
     * @param button the button to add the tooltip to.
     * @param message the message to be displayed in the tooltip.
     */
    private void addToolTip(Button button, String message) {
        button.setTooltip(new Tooltip(message));
    }

    /**
     * changes the delay time between hovering over something with a tooltip and when the
     * tooltip is displayed.
     * @param tooltip the tooltip to change the delay of
     */
    private static void hackTooltipStartTiming(Tooltip tooltip) {

        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(150)));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    /**
     * Sorts the scores of users.
     * @param users the list of users
     */
    public void sortScores(List<String> users) throws InterruptedException {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                Double first = userService.getFootprint(users.get(i));
                Double second = userService.getFootprint(users.get(j));
                if (i < j && (first.compareTo(second) < 0)) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
                if (i > j && (first.compareTo(second) > 0)) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
    }

    /**
     * Sorts the scores of users.
     * @param users the list of users
     */
    public List<String> sortDiffScores(List<String> users) throws InterruptedException {
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                Double firstDiff = userService.getFirstFootprint(users.get(i)) - userService
                        .getFootprint(users.get(i));
                Double secondDiff = userService.getFirstFootprint(users.get(j)) - userService
                        .getFootprint(users.get(j));
                if (i < j && firstDiff < secondDiff) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
                if (i > j && firstDiff > secondDiff) {
                    String temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
        return users;
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
        updateAchievements();
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
        income.setText(inputMap.get("input_income") + " €/yr");
        electricityUsage.setText(inputMap.get("input_footprint_housing_electricity_dollars")
                + " €/yr");
        cleanEnergy.setText(inputMap.get("input_footprint_housing_gco2_per_kwh"));
        naturalGasUsage.setText(inputMap.get("input_footprint_housing_naturalgas_dollars")
                + " €/yr");
        heatingOilUsage.setText(inputMap.get("input_footprint_housing_heatingoil_dollars")
                + " €/yr");
        waterUsage.setText(inputMap.get("input_footprint_housing_watersewage") + " €/yr");
        livingSpace.setText(inputMap.get("input_footprint_housing_squarefeet") + " m²");
        gasolineMiles.setText(inputMap.get("input_footprint_transportation_miles1"));
        gasolineMpg.setText(inputMap.get("input_footprint_transportation_mpg1"));
        dieselMiles.setText(inputMap.get("input_footprint_transportation_miles2"));
        dieselMpg.setText(inputMap.get("input_footprint_transportation_mpg2"));
        electricMiles.setText(inputMap.get("input_footprint_transportation_miles3"));
        electricMpg.setText(inputMap.get("input_footprint_transportation_mpg3"));
        publicTransportation.setText(inputMap.get("input_footprint_transportation_publictrans")
                + " km/yr");
        airPlane.setText(inputMap.get("input_footprint_transportation_airtotal") + " km/yr");
        goodShopping.setText(inputMap.get("input_footprint_shopping_goods_total") + " €/mo");
        serviceShopping.setText(inputMap.get("input_footprint_shopping_services_total") + " €/mo");
        meat.setText(inputMap.get("input_footprint_shopping_food_meatfisheggs"));
        grains.setText(inputMap.get("input_footprint_shopping_food_cereals"));
        dairy.setText(inputMap.get("input_footprint_shopping_food_dairy"));
        fruits.setText(inputMap.get("input_footprint_shopping_food_fruitvegetables"));
        snacks.setText(inputMap.get("input_footprint_shopping_food_otherfood"));
        Map<String, String> extraMap = userService
                .getExtraInputs(userService.currentUser.getName());
        veganMeal.setText(extraMap.get("vegan"));
        localProduce.setText(extraMap.get("local_produce"));
        bike.setText(extraMap.get("bike"));
        publicTrans.setText(extraMap.get("public_transport"));
        loweringTemp.setText(extraMap.get("temperature"));
        solarPanels.setText(extraMap.get("solar_panels"));
    }

    /**
     * Displays the user profile pane.
     * @param event the event (clicking the button)
     */
    public void displayUser(ActionEvent event) throws InterruptedException {
        System.out.println(userService.currentUser.getName());
        System.out.println(userService.getFootprint(userService.currentUser.getName()));
        footprintLabel.setText("" + userService.getFootprint(userService.currentUser.getName()));
        firstFootprintLabel.setText("" + userService
                .getFirstFootprint(userService.currentUser.getName()));
        Double diff = userService.getFirstFootprint(userService.currentUser.getName()) - userService
                .getFootprint(userService.currentUser.getName());
        differenceLabel.setText( "" + Math.round(diff * 10) / 10.0);
        usernameLabel.setText("" + userService.currentUser.getName());
        addFadeTransition(userPane);
        System.out.println("display user");
        dashboardPane.setVisible(false);
        userPane.setVisible(true);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(false);
        updatePiechart();
    }

    /**
     * Displays the friends pane.
     * @param event the event (clicking the button)
     */
    public void displayFriends(ActionEvent event) throws InterruptedException {
        addFadeTransition(friendsPane);
        System.out.println("display friends");
        dashboardPane.setVisible(false);
        userPane.setVisible(false);
        activitiesPane.setVisible(false);
        friendsPane.setVisible(true);
        updateFriends();
    }


    /**
     * Logs out the user.
     * @param event the event (clicking the button)
     * @throws IOException if the Application doesn't load.
     */
    public void logOut(ActionEvent event) throws IOException {
        //get the current window
        Window owner = logOutButton.getScene().getWindow();
        Stage current = (Stage) owner;
        //close current window (log out)
        current.close();
        System.out.println("User is logged out");

        //global leaderboard
        globalLeaderboard.getItems().clear();
        globalLeaderData.removeAll();
        //development leaderboard
        developmentLeaderboard.getItems().clear();
        developmentData.removeAll();
        //friends leaderboard
        friendLeaderboard.getItems().clear();
        friendLeaderData.removeAll();

        //load the fxml file
        Parent dash = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/LoginWindow.fxml"));
        Scene scene = new Scene(dash);
        //add the stylesheet for the CSS
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("stylesheets/LoginWindowStyle.css")).toExternalForm());
        Stage appStage = new Stage();
        appStage.setScene(scene);
        //set the title
        appStage.setTitle("Greenify");
        appStage.show();
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
    @SuppressWarnings("Duplicates")
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
     * This method adds a random hint to the side bar.
     */
    public void addRandomHints() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), hintText);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.play();
        hintText.setWrapText(true);
        hintText.setText(hints.randomHint());
        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), hintText);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    /**
     * Opens extra activities.
     * @param event the event (clicking the button)
     * @throws IOException if the Application doesn't load.
     */
    public void openExtraActivities(ActionEvent event) throws IOException {
        Parent extra = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/extraActivities.fxml"));
        Scene scene = new Scene(extra);
        Stage extraStage = new Stage();
        extraStage.setScene(scene);
        extraStage.setTitle("Add extra activity - " + userService.currentUser.getName());
        extraStage.show();

    }

    /**
     * method opens addFriend scene.
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
     * method opens removeFriend scene.
     * @throws IOException when file is not found
     */
    public void openRemoveFriend() throws IOException {
        Parent calc = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/RemoveFriend.fxml"));
        Scene scene = new Scene(calc);
        Stage calcStage = new Stage();
        calcStage.setScene(scene);
        calcStage.setTitle("Remove your friend - " + userService.currentUser.getName());
        calcStage.show();
    }

    /**
     * Leaderboard is updating.
     * @throws InterruptedException throws exception
     */
    @SuppressWarnings("Duplicates")
    public void updateLeaderboard() throws InterruptedException {
        //global leaderboard
        globalLeaderboard.getItems().clear();
        globalLeaderData.removeAll();
        //development leaderboard
        developmentLeaderboard.getItems().clear();
        developmentData.removeAll();
        List<String> userList = userService.getAllUsers();
        sortScores(userList);
        for (int i = userList.size() - 1; i >= 0; i--) {
            Friend user = new Friend(userList.size() - i, userList.get(i),
                    userService.getFootprint(userList.get(i)));
            globalLeaderData.add(user);
        }
        List<String> secondList = sortDiffScores(userList);
        for (int j = 0; j < userList.size(); j++) {
            double diff = Math.round((userService.getFirstFootprint(secondList.get(j))
                    - userService.getFootprint(secondList.get(j))) * 10) / 10.0;
            Friend diffUser = new Friend(j + 1, secondList.get(j), diff);
            developmentData.add(diffUser);
        }
        globalLeaderboard.setItems(globalLeaderData);
        developmentLeaderboard.setItems(developmentData);
    }

    /**
     * The method updating piechart.
     * @throws InterruptedException exception
     */
    public void updatePiechart() throws InterruptedException {
        Map<String, String> resultMap = userService.getResults(userService.currentUser.getName());
        if (pieChart != null) {
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Transport",
                                    Double.parseDouble(resultMap.get("transport"))),
                            new PieChart.Data("Housing",
                                    Double.parseDouble(resultMap.get("housing"))),
                            new PieChart.Data("Food",
                                    Double.parseDouble(resultMap.get("food"))),
                            new PieChart.Data("Goods",
                                    Double.parseDouble(resultMap.get("goods"))),
                            new PieChart.Data("Services",
                                    Double.parseDouble(resultMap.get("services")))
                    );
            pieChart.setTitle("FOOTPRINT DISTRIBUTION");
            pieChart.setMaxSize(1000, 1000);
            pieChart.setData(pieChartData);
        }
    }

    /**
     * Friends tables are updating.
     * @throws InterruptedException throws exception
     */
    @SuppressWarnings("Duplicates")
    public void updateFriends() throws InterruptedException {
        List<String> wholeList = userService.getFriendNames(userService.currentUser.getName());
        wholeList.add(userService.currentUser.getName());
        //friend list
        friendsTable.getItems().clear();
        data.removeAll();
        List<String> friendList = userService.getFriendNames(userService.currentUser.getName());
        sortScores(friendList);
        //friends leaderboard
        friendLeaderboard.getItems().clear();
        friendLeaderData.removeAll();
        sortDiffScores(wholeList);
        for (int i = friendList.size() - 1; i >= 0 ; i--) {
            Friend user = new Friend(i, friendList.get(i), userService
                    .getFootprint(friendList.get(i)));
            data.add(user);
        }
        for (int j = 0; j < wholeList.size(); j++) {
            double diff = Math.round((userService.getFirstFootprint(wholeList.get(j))
                    - userService.getFootprint(wholeList.get(j))) * 10) / 10.0;
            Friend diffUser = new Friend(j + 1, wholeList.get(j), diff);
            friendLeaderData.add(diffUser);
        }
        friendsTable.setItems(data);
        friendLeaderboard.setItems(friendLeaderData);
    }

    /**
     * Updates the achievements.
     */
    public void updateAchievements() {
        Map achievements = userService.getAchievements(userService.currentUser.getName());
        ColorAdjust desaturate = new ColorAdjust();
        desaturate.setSaturation(-0.75);
        if ((Boolean)achievements.get("Starting off")) {
            achieve1.setOpacity(1);
        } else {
            achieve1.setEffect(desaturate);
            achieve1.setOpacity(0.3);
        }
        if ((Boolean)achievements.get("Social butterfly")) {
            achieve2.setOpacity(1);
        } else {
            achieve2.setEffect(desaturate);
            achieve2.setOpacity(0.3);
        }
        if ((Boolean)achievements.get("Green saver")) {
            achieve3.setOpacity(1);
        } else {
            achieve3.setEffect(desaturate);
            achieve3.setOpacity(0.3);
        }
        if ((Boolean)achievements.get("Animal friend")) {
            achieve4.setOpacity(1);
        } else {
            achieve4.setEffect(desaturate);
            achieve4.setOpacity(0.3);
        }
        if ((Boolean)achievements.get("Tom Dumoulin")) {
            achieve5.setOpacity(1);
        } else {
            achieve5.setEffect(desaturate);
            achieve5.setOpacity(0.3);
        }
        if ((Boolean)achievements.get("Let it shine")) {
            achieve6.setOpacity(1);
        } else {
            achieve6.setEffect(desaturate);
            achieve6.setOpacity(0.3);
        }
    }

    //class for the animations on the navigation buttons
    private class MyButtonSkin extends ButtonSkin {
        /**
         * adds a skin and scale animation to a button.
         * the scale transition is for hovering over it so it then scales up
         * and scales down when you stop hovering over it.
         * @param button the button to add the animation to
         */
        private MyButtonSkin(Button button) {
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

    @SuppressWarnings("Duplicates")
    private class ClickButtonSkin extends ButtonSkin {
        /**
         * button skin for the 'add activity' buttons.
         * adds scale animations on entering, clicking and exiting the button
         * @param button the button to set the skin of
         */
        private ClickButtonSkin(Button button) {
            super(button);

            //transition to scale up on hover
            final ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100));
            //add the node and the position to scale to
            scaleUp.setNode(button);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            //play the transition when hovered over the button
            button.setOnMouseEntered(e -> scaleUp.playFromStart());

            final ScaleTransition scaleMiddleDown = new ScaleTransition(Duration.millis(50));
            scaleMiddleDown.setNode(button);
            scaleMiddleDown.setToX(1.05);
            scaleMiddleDown.setToY(1.05);

            button.setOnMousePressed(e -> scaleMiddleDown.playFromStart());

            final ScaleTransition scaleMiddleUp = new ScaleTransition(Duration.millis(50));
            scaleMiddleUp.setNode(button);
            scaleMiddleUp.setToX(1.1);
            scaleMiddleUp.setToY(1.1);
            button.setOnMouseReleased(e -> scaleMiddleUp.playFromStart());

            final ScaleTransition scaleDown = new ScaleTransition(Duration.millis(100));
            scaleDown.setNode(button);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            button.setOnMouseExited(e -> scaleDown.playFromStart());
        }
    }
}
