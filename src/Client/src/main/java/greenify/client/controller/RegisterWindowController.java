package greenify.client.controller;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import greenify.client.Application;
import greenify.client.rest.UserService;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * Class that controls the actions for the register window.
 */
@Controller
public class RegisterWindowController {

    @Autowired
    UserService userService;

    @Autowired
    DashBoardController controller;

    //navigation panes
    @FXML
    private AnchorPane getStartedPane;
    @FXML
    private AnchorPane travelPane;
    @FXML
    private AnchorPane homePane;
    @FXML
    private ScrollPane foodPane;
    @FXML
    private AnchorPane shoppingPane;

    //'get started' pane
    @FXML
    private Slider peopleInHouseholdSlider;
    @FXML
    private Label peopleInHouseHoldLabel;
    @FXML
    private Slider annualIncomeSlider;
    @FXML
    private Label annualIncomeLabel;
    @FXML
    private Button saveButton;

    //travel pane
    @FXML
    private TextField publicTransitField;
    @FXML
    private TextField airplaneTravelField;
    @FXML
    private TextField carTravelGasolineField;
    @FXML
    private Slider carTravelGasolineSlider;
    @FXML
    private Label carTravelGasolineLabel;
    @FXML
    private TextField carTravelDieselField;
    @FXML
    private Slider carTravelDieselSlider;
    @FXML
    private Label carTravelDieselLabel;
    @FXML
    private TextField carTravelElectricField;
    @FXML
    private Slider carTravelElectricSlider;
    @FXML
    private Label carTravelElectricLabel;

    //home pane
    @FXML
    private TextField electricityField;
    @FXML
    private Slider cleanEnergyPurchasedSlider;
    @FXML
    private Label cleanEnergyPurchasedLabel;
    @FXML
    private TextField naturalGasField;
    @FXML
    private TextField heatingOilField;
    @FXML
    private TextField livingSpaceField;
    @FXML
    private Slider waterUsageSlider;
    @FXML
    private Label waterUsageLabel;

    //food pane
    @FXML
    private Slider meatFishEggsSlider;
    @FXML
    private Label meatFishEggsLabel;
    @FXML
    private Slider grainsBakedGoodsSlider;
    @FXML
    private Label grainsBakedGoodsLabel;
    @FXML
    private Slider dairySlider;
    @FXML
    private Label dairyLabel;
    @FXML
    private Slider fruitsVegetablesSlider;
    @FXML
    private Label fruitsVegetablesLabel;
    @FXML
    private Slider snacksDrinksSlider;
    @FXML
    private Label snacksDrinksLabel;

    //shopping pane
    @FXML
    private Text goodsField;
    @FXML
    private Text servicesField;
    @FXML
    private Slider goodsSlider;
    @FXML
    private Label goodsLabel;
    @FXML
    private Slider servicesSlider;
    @FXML
    private Label servicesLabel;

    //extra pane
    @FXML
    private AnchorPane extraPane;
    @FXML
    private CheckBox localProduceCheckbox;
    @FXML
    private CheckBox bikeCheckbox;
    @FXML
    private CheckBox temperatureCheckbox;
    @FXML
    private CheckBox solarPanelsCheckbox;

    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField2;
    @FXML
    private Button signUpButton;
    //@FXML
    //private Line uNamePathLine;

    /**
     * Initializes the animation.
     * @throws InterruptedException exception when interrupted
     */
    public void initialize() throws InterruptedException {
        addSlideAnimation(1100, userNameText, -300);
        addSlideAnimation(1100, passwordField, 300);
        TimeUnit.MILLISECONDS.sleep(300);
        addSlideAnimation(1100, passwordField2, -420);
        signUpButton.setSkin(new SignUpButtonSkin(signUpButton));
    }

    /**
     * Adds the slide animation.
     * @param duration the duration
     * @param node the node
     * @param from from where
     */
    private void addSlideAnimation(int duration, Node node, int from) {
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(duration), node);
        slideIn.setFromX(from);
        slideIn.setToX(0);
        slideIn.play();
    }

    /**
     * Signs up the user.
     * @param event the click of the sign up button
     */
    @FXML
    public void handleSignUpButton(ActionEvent event) throws IOException {
        //set the window to the current window (for displaying the alerts)
        Window owner = signUpButton.getScene().getWindow();
        //check if the username field is empty
        if (userNameText.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Username Error!",
                    "Please enter a username!");
            return;
        }

        //check if the password field is empty
        if (passwordField.getText().isEmpty()) {
            //if so, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                    "Please enter a password!");
            return;
        }

        //check if the two password fields are equal
        if (!passwordField.getText().equals(passwordField2.getText())) {
            //if not, display an alert
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Password Error!",
                    "Please make sure the passwords entered are the same!");
            return;
        }

        //register the user with the provided username and password
        try {
            userService.registerUser(userNameText.getText(), passwordField.getText());
        } catch (RuntimeException ex) {
            UserController.AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Username Error!",
                    "This username has already been taken!");
            return;
        }
        //close the register window after the user has entered all the credentials
        Stage current = (Stage) owner;
        current.close();

        Parent calc = Application.load(this.getClass().getClassLoader()
                .getResource("fxml/FirstCalculator.fxml"));
        Scene scene = new Scene(calc);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("stylesheets/calculatorStyle.css").toExternalForm());
        Stage calcStage = new Stage();

        calcStage.setScene(scene);
        calcStage.setTitle("Calculate CO2 footprint - " + userService.currentUser.getName());
        calcStage.show();

        peopleInHouseholdSlider.setSnapToTicks(true);
        //add listener to slider for amount of people in household
        peopleInHouseholdSlider.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                peopleInHouseHoldLabel.setText("" + newValue.intValue());
            }
        });

        //add listener to slider for annual income
        annualIncomeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                annualIncomeLabel.setText("" + (newValue.intValue() * 1000));
            }
        });

        addSliderListenerCarUsage(carTravelGasolineSlider, carTravelGasolineLabel, " mpg");
        addSliderListenerCarUsage(carTravelDieselSlider, carTravelDieselLabel, " mpg");
        addSliderListenerCarUsage(carTravelElectricSlider, carTravelElectricLabel, " mpge");

        cleanEnergyPurchasedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                cleanEnergyPurchasedLabel.setText(newValue.intValue() + " %");
            }
        });

        waterUsageSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                waterUsageLabel.setText(newValue.intValue() + "% of similar households");
            }
        });

        addSliderListenerDailyServing(meatFishEggsSlider, meatFishEggsLabel);
        addSliderListenerDailyServing(grainsBakedGoodsSlider, grainsBakedGoodsLabel);
        addSliderListenerDailyServing(dairySlider, dairyLabel);
        addSliderListenerDailyServing(fruitsVegetablesSlider, fruitsVegetablesLabel);
        addSliderListenerDailyServing(snacksDrinksSlider, snacksDrinksLabel);

        addSliderListenerGoodsServices(goodsSlider, goodsLabel);
        addSliderListenerGoodsServices(servicesSlider, servicesLabel);
    }

    /**
     * adds the listener to the given slider and displays it's output on a given label.
     * @param slider the slider to attach the listener to.
     * @param label the label to display the slider output on.
     */
    private void addSliderListenerCarUsage(Slider slider, Label label, String unit) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                label.setText(newValue.intValue() + unit);
            }
        });
    }

    private void addSliderListenerDailyServing(Slider slider, Label label) {
        DecimalFormat df = new DecimalFormat("0.0");
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                label.setText(df.format(newValue.doubleValue()) + " daily servings per person");
            }
        });
    }

    private void addSliderListenerGoodsServices(Slider slider, Label label) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                label.setText(newValue.intValue() + "€ / month");
            }
        });
    }

    private void addSlideInAnimation(Node node) {
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(node.translateXProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(600), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * displays the 'get started' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    @SuppressWarnings("Duplicates")
    public void displayGetStarted(ActionEvent event) {
        getStartedPane.setVisible(true);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        extraPane.setVisible(false);
    }

    /**
     * displays the 'travel' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    @SuppressWarnings("Duplicates")
    public void displayTravel(ActionEvent event) {
        addSlideInAnimation(travelPane);
        getStartedPane.setVisible(false);
        travelPane.setVisible(true);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        extraPane.setVisible(false);

    }

    /**
     * displays the 'home' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    @SuppressWarnings("Duplicates")
    public void displayHome(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(true);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        extraPane.setVisible(false);
    }

    /**
     * displays the 'food' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    @SuppressWarnings("Duplicates")
    public void displayFood(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(true);
        shoppingPane.setVisible(false);
        extraPane.setVisible(false);
    }

    /**
     * displays the 'shopping' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    @SuppressWarnings("Duplicates")
    public void displayShopping(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(true);
        extraPane.setVisible(false);
    }

    /**
     * displays the 'Extra' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the designated button
     */
    @SuppressWarnings("Duplicates")
    public void displayExtra(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        extraPane.setVisible(true);

    }

    /**
     * The method saves the calculation.
     * @param event user clicks to button
     */
    @SuppressWarnings("Duplicates")
    public void saveCalc(ActionEvent event) throws InterruptedException {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        if (!annualIncomeLabel.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(), "input_income",
                    annualIncomeLabel.getText());
        }
        if (!peopleInHouseHoldLabel.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(), "input_size",
                    peopleInHouseHoldLabel.getText());
        }
        checkTransportLabels();
        checkHousingLabels();
        checkFoodLabels();
        if (!goodsLabel.getText().replace(" € / month", "").equals("1520")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_goods_total",
                    goodsLabel.getText().replace("€ / month", ""));
        }
        if (!servicesLabel.getText().replace(" € / month", "").equals("3428")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_services_total",
                    servicesLabel.getText().replace("€ / month", ""));
        }
        if (localProduceCheckbox.isSelected()) {
            userService.updateExtraInput(userService.currentUser.getName(),
                    "local_produce", true);
        }
        if (bikeCheckbox.isSelected()) {
            userService.updateExtraInput(userService.currentUser.getName(),
                    "bike", true);
        }
        if (temperatureCheckbox.isSelected()) {
            userService.updateExtraInput(userService.currentUser.getName(),
                    "temperature", true);
        }
        if (solarPanelsCheckbox.isSelected()) {
            userService.updateExtraInput(userService.currentUser.getName(),
                    "solar_panels", true);
        }
        Float firstFootprint = userService.saveFirstFootprint(userService.currentUser.getName());
        Float footprint = userService.saveFootprint(userService.currentUser.getName());
        Window owner = saveButton.getScene().getWindow();
        Stage current = (Stage) owner;
        current.close();
    }

    /**
     * Checks the food labels.
     */
    public void checkFoodLabels() {
        if (!meatFishEggsLabel.getText().replace(" daily servings per person", "").equals("2.6")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_food_meatfisheggs",
                    meatFishEggsLabel.getText().replace(" daily servings per person", ""));
        }
        if (!grainsBakedGoodsLabel.getText()
                .replace(" daily servings per person", "").equals("4.4")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_food_cereals",
                    grainsBakedGoodsLabel.getText().replace(" daily servings per person", ""));
        }
        if (!dairyLabel.getText().replace(" daily servings per person", "").equals("2.4")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_food_dairy",
                    dairyLabel.getText().replace(" daily servings per person", ""));
        }
        if (!fruitsVegetablesLabel.getText()
                .replace(" daily servings per person", "").equals("3.9")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_food_fruitvegetables",
                    fruitsVegetablesLabel.getText().replace(" daily servings per person", ""));
        }
        if (!snacksDrinksLabel.getText().replace(" daily servings per person", "").equals("3.7")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_shopping_food_otherfood",
                    snacksDrinksLabel.getText().replace(" daily servings per person", ""));
        }
    }

    /**
     * Checks the housing labels.
     */
    public void checkHousingLabels() {
        if (!electricityField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_electricity_dollars",
                    electricityField.getText());
        }
        if (!cleanEnergyPurchasedLabel.getText().replace(" %", "").equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_gco2_per_kwh",
                    cleanEnergyPurchasedLabel.getText().replace(" %", ""));
        }
        if (!naturalGasField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_naturalgas_dollars",
                    naturalGasField.getText());
        }
        if (!heatingOilField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_heatingoil_dollars",
                    heatingOilField.getText());
        }
        if (!livingSpaceField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_squarefeet",
                    livingSpaceField.getText());
        }
        if (!waterUsageLabel.getText().replace("% of similar households", "").equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_housing_watersewage",
                    waterUsageLabel.getText().replace("% of similar households", ""));
        }
    }

    /**
     * Checks the transport labels.
     */
    public void checkTransportLabels() {
        if (!publicTransitField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_publictrans",
                    publicTransitField.getText());
        }
        if (!airplaneTravelField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_airtotal",
                    airplaneTravelField.getText());
        }
        if (!carTravelGasolineField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_miles1",
                    carTravelGasolineField.getText());
        }
        if (!carTravelGasolineLabel.getText().replace(" mpg", "").equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_mpg1",
                    carTravelGasolineLabel.getText().replace(" mpg", ""));
        }
        if (!carTravelDieselField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_miles2",
                    carTravelDieselField.getText());
        }
        if (!carTravelDieselLabel.getText().replace(" mpg", "").equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_mpg2",
                    carTravelDieselLabel.getText().replace(" mpg", ""));
        }
        if (!carTravelElectricField.getText().equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_miles3",
                    carTravelElectricField.getText());
        }
        if (!carTravelElectricLabel.getText().replace(" mpge", "").equals("0")) {
            userService.updateInput(userService.currentUser.getName(),
                    "input_footprint_transportation_mpg3",
                    carTravelElectricLabel.getText().replace(" mpge", ""));
        }
    }

    @SuppressWarnings("Duplicates")
    private class SignUpButtonSkin extends ButtonSkin {
        /**
         * button skin for the 'add activity' buttons.
         * adds scale animations on entering, clicking and extiting the button
         * @param button the button to set the skin of
         */
        private SignUpButtonSkin(Button button) {
            super(button);

            //transition to scale up on hover
            final ScaleTransition scaleUp = new ScaleTransition(Duration.millis(85));
            //add the node and the position to scale to
            scaleUp.setNode(button);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            //play the transition when hovered over the button
            button.setOnMouseEntered(e -> scaleUp.playFromStart());

            final ScaleTransition scaleDown = new ScaleTransition(Duration.millis(85));
            scaleDown.setNode(button);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            button.setOnMouseExited(e -> scaleDown.playFromStart());
        }
    }
}
