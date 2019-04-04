package greenify.client.controller;

import com.sun.javafx.scene.control.skin.ButtonSkin;
import greenify.client.rest.UserService;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
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

        addVeganMealButton.setSkin(new ActivityButtonSkin(addVeganMealButton));
        addBikeButton.setSkin(new ActivityButtonSkin(addBikeButton));
        addTemperatureButton.setSkin(new ActivityButtonSkin(addTemperatureButton));
        addSolarPanelsButton.setSkin(new ActivityButtonSkin(addSolarPanelsButton));

        displayVeganMealButton.setSkin(new TranslateButtonSkin(displayVeganMealButton));
        displayBikeButton.setSkin(new TranslateButtonSkin(displayBikeButton));
        displayTemperatureButton.setSkin(new TranslateButtonSkin(displayTemperatureButton));
        displaySolarPanelButton.setSkin(new TranslateButtonSkin(displaySolarPanelButton));

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

    public class TranslateButtonSkin extends ButtonSkin {
        public TranslateButtonSkin(Button button) {
            super(button);

            TranslateTransition transEnter = new TranslateTransition(Duration.millis(50));
            transEnter.setNode(button);
            transEnter.setToX(10);
            button.setOnMouseEntered(e -> transEnter.playFromStart());

            TranslateTransition transExit = new TranslateTransition(Duration.millis(50));
            transExit.setNode(button);
            transExit.setToX(1.0);
            button.setOnMouseExited(e -> transExit.playFromStart());

        }
    }

    @SuppressWarnings("Duplicates")
    public class ActivityButtonSkin extends ButtonSkin {
        public ActivityButtonSkin(Button button) {
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
