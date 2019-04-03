package greenify.client.controller;

import greenify.client.Application;
import greenify.client.rest.UserService;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.DecimalFormat;

@Controller
public class CalculatorController {
    private static UserController.AlertHelper AlertHelper;
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


    /**
     * initializes the window, performs some actions before loading all other things.
     * it sets the sliders to snap to the ticks.
     * it adds listeners to all the sliders for updating the label they are associated with.
     */
    @SuppressWarnings("Duplicates")
    public void initialize() {
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

        addSliderListenerCarUsage(carTravelGasolineSlider, carTravelGasolineLabel, " km/L");
        addSliderListenerCarUsage(carTravelDieselSlider, carTravelDieselLabel, " km/L");
        addSliderListenerCarUsage(carTravelElectricSlider, carTravelElectricLabel, " km/Le");

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

    @SuppressWarnings("Duplicates")
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

    /**
     * adds a fade transition to the given node.
     * @param node the node to add the transition to
     */
    public void addFadeTransAnimation(Node node) {
        FadeTransition fade = new FadeTransition(Duration.millis(350), node);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        TranslateTransition trans = new TranslateTransition(Duration.millis(350), node);
        trans.setFromY(800);
        trans.setToY(0);
        ParallelTransition par = new ParallelTransition();
        par.setNode(travelPane);
        par.getChildren().addAll(fade, trans);
        par.play();
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
        addFadeTransAnimation(getStartedPane);
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
        addFadeTransAnimation(travelPane);
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
        addFadeTransAnimation(homePane);
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
        addFadeTransAnimation(foodPane);
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
        addFadeTransAnimation(shoppingPane);
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
    public void displayExtra(ActionEvent event) throws IOException {
        addFadeTransAnimation(extraPane);
        extraPane.getChildren().setAll((Node) Application.load(this.getClass()
                .getClassLoader().getResource("fxml/extraActivities.fxml")));
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
            localProduceCheckbox.setSelected(true);
            userService.updateExtraInput(userService.currentUser.getName(),
                    "local_produce", true);
        }
        if (bikeCheckbox.isSelected()) {
            bikeCheckbox.setSelected(true);
            userService.updateExtraInput(userService.currentUser.getName(),
                    "bike", true);
        }
        if (temperatureCheckbox.isSelected()) {
            temperatureCheckbox.setSelected(true);
            userService.updateExtraInput(userService.currentUser.getName(),
                    "temperature", true);
        }
        if (solarPanelsCheckbox.isSelected()) {
            solarPanelsCheckbox.setSelected(true);
            userService.updateExtraInput(userService.currentUser.getName(),
                    "solar_panels", true);
        }
        Float footprint = userService.saveFootprint(userService.currentUser.getName());
        Window owner = saveButton.getScene().getWindow();
        Stage current = (Stage) owner;
//        CalculatorController.AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Footprint saved!", "Your footprint is saved!");
        current.close();
        controller.updateLeaderboard();
    }
}