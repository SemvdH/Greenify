package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExtraActivityController {

    @Autowired
    UserService userService;

    @FXML
    private AnchorPane veganMealPane;
    @FXML
    private AnchorPane bikePane;
    @FXML
    private AnchorPane temperaturePane;
    @FXML
    private AnchorPane solarPanelPane;
    @FXML
    private Button displayVeganMealButton;
    @FXML
    private Button displayBikeButton;
    @FXML
    private Button displayTemperatureButton;
    @FXML
    private Button displaySolarPanelButton;

    @FXML
    private Button addVeganMealButton;
    @FXML
    private Button addBikeButton;
    @FXML
    private Button addTemperatureButton;
    @FXML
    private Button addSolarPanelsButton;
    @FXML
    private Slider bikeSlider;
    @FXML
    private Label bikeLabel;
    @FXML
    private Slider temperatureSlider;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Slider solarPanelsSlider;
    @FXML
    private Label solarPanelsLabel;

    /**
     * initializes the sliders and labels before loading.
     * sets the labels to display the outputs of the designated sliders.
     */
    public void initialize() {
        coupleSliderToLabel(bikeSlider, bikeLabel, " km", false);
        coupleSliderToLabel(temperatureSlider, temperatureLabel, " Degrees", true);
        coupleSliderToLabel(solarPanelsSlider, solarPanelsLabel, "", true);

    }

    /**
     * Sets the label to display the value of the designated slider.
     * sets the text to be displayed after the value of the slider.
     * sets whether the slider should snap to ticks
     * @param slider the slider to read the value from
     * @param label the label to display the value of the slider
     * @param string the string to be placed after the outputted value of the slider
     * @param snapToTicks whether the slider should snap to ticks or not
     */
    public void coupleSliderToLabel(Slider slider, Label label, String string,
                                    boolean snapToTicks) {
        slider.setSnapToTicks(snapToTicks);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue, Number newValue) {
                label.setText(newValue.intValue() + string);
            }
        });
    }

    /**
     * displays the vegetarian meal section.
     * @param event the click of the designated button
     */
    public void displayVeganMeal(ActionEvent event) {
        // System.out.println("display vm");
        veganMealPane.setVisible(true);
        bikePane.setVisible(false);
        temperaturePane.setVisible(false);
        solarPanelPane.setVisible(false);
    }

    /**
     * displays the bike section.
     * @param event the click of the designated button
     */
    public void displayBike(ActionEvent event) {
        // System.out.println("display b");
        veganMealPane.setVisible(false);
        bikePane.setVisible(true);
        temperaturePane.setVisible(false);
        solarPanelPane.setVisible(false);
    }

    /**
     * displays the temperature section.
     * @param event the click of the designated button
     */
    public void displayTemperature(ActionEvent event) {
        // System.out.println("display t");
        veganMealPane.setVisible(false);
        bikePane.setVisible(false);
        temperaturePane.setVisible(true);
        solarPanelPane.setVisible(false);
    }

    /**
     * displays the solar panels section.
     * @param event the click of the designated button
     */
    public void displaySolarPanel(ActionEvent event) {
        // System.out.println("display sp");
        veganMealPane.setVisible(false);
        bikePane.setVisible(false);
        temperaturePane.setVisible(false);
        solarPanelPane.setVisible(true);
    }
}
