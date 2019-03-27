package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
    @Autowired
    UserService userService;

    @FXML
    private Button calculatorGetStartedButton;
    @FXML
    private Button calculatorTravelButton;
    @FXML
    private Button calculatorHomeButton;
    @FXML
    private Button calculatorFoodButton;
    @FXML
    private Button calculatorShoppingButton;
    @FXML
    private AnchorPane getStartedPane;
    @FXML
    private AnchorPane travelPane;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane foodPane;
    @FXML
    private AnchorPane shoppingPane;
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
    @FXML
    private TextField publicTransitField;
    @FXML
    private TextField airplaneTravelField;

    /**
     * initializes the window, performs some actions before loading all other things.
     * it sets the sliders to snap to the ticks
     * it adds listeners to all the sliders for updating the label next to them
     */
    public void initialize() {
        peopleInHouseholdSlider.setSnapToTicks(true);
        annualIncomeSlider.setSnapToTicks(true);
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

    /**
     * The method saves the calculation.
     * @param event user clicks to button
     */
    public void saveCalculation(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
        if (!annualIncomeLabel.getText().equals(null)) {
            userService.updateInput(userService.currentUser.getName(), "input_income",
                    annualIncomeLabel.getText());
        }
        if (!peopleInHouseHoldLabel.getText().equals(null)) {
            userService.updateInput(userService.currentUser.getName(), "input_population",
                    peopleInHouseHoldLabel.getText());
        }
        userService.updateInput(userService.currentUser.getName(),
                "input_footprint_housing_naturalgas_cuft", "0");
        userService.updateInput(userService.currentUser.getName(),
                "input_footprint_transportation_miles1", "0");
    }
}

