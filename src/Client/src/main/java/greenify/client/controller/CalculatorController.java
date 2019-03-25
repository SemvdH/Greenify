package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
    @Autowired
    UserService userService;

    @FXML
    public Button calculatorGetStartedButton;
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
    private ScrollPane travelPane;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane foodPane;
    @FXML
    private AnchorPane shoppingPane;
    @FXML
    private Slider peopleInHouseholdSLider;
    @FXML
    private Label peopleInHouseHoldLabel;
    @FXML
    private Slider annualIncomeSlider;
    @FXML
    private Label annualIncomeLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button getStartedNextButton;

    public void initialize() {
        peopleInHouseholdSLider.setSnapToTicks(true);
        annualIncomeSlider.setSnapToTicks(true);
        //add listener to slider for amount of people in household
        peopleInHouseholdSLider.valueProperty().addListener(new ChangeListener<Number>() {

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

    public void displayGetStarted(ActionEvent event) {
        getStartedPane.setVisible(true);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);

    }

    public void displayTravel(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(true);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
    }

    public void displayHome(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(true);
        foodPane.setVisible(false);
        shoppingPane.setVisible(false);
    }

    public void displayFood(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(true);
        shoppingPane.setVisible(false);
    }

    public void displayShopping(ActionEvent event) {
        getStartedPane.setVisible(false);
        travelPane.setVisible(false);
        homePane.setVisible(false);
        foodPane.setVisible(false);
        shoppingPane.setVisible(true);
    }








}
