package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;

@Controller
public class CalculatorController {
    @Autowired
    UserService userService;

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
    private Slider cleanEnergyPurchasedSlider;
    @FXML
    private Label cleanEnergyPurchasedLabel;
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
    private Slider goodsSlider;
    @FXML
    private Label goodsLabel;
    @FXML
    private Slider servicesSlider;
    @FXML
    private Label servicesLabel;

    /**
     * initializes the window, performs some actions before loading all other things.
     * it sets the sliders to snap to the ticks.
     * it adds listeners to all the sliders for updating the label they are associated with.
     */
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
    public void displayGetStarted(ActionEvent event) {
        getStartedPane.setVisible(true);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);

    }

    /**
     * displays the 'travel' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    public void displayTravel(ActionEvent event) {
        addSlideInAnimation(travelPane);
        getStartedPane.setVisible(false);
        travelPane.setVisible(true);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);


    }

    /**
     * displays the 'home' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    public void displayHome(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(true);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
    }

    /**
     * displays the 'food' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    public void displayFood(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(true);
        shoppingPane.setVisible(false);
    }

    /**
     * displays the 'shopping' section of the calculator.
     * Activated when the designated button (navigation button) is clicked
     * @param event the click of the button
     */
    public void displayShopping(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(true);
    }








}
