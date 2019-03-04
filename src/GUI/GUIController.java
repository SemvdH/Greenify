package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GUIController {
    private int pressed;

    //put @FXML to let the fxml file know that it can use this label,
    //kind of the same as with @Test in jUnit tests

    /**
     *the label to be edited by pressing the button
     */
    @FXML
    private Label txtLabel;

    /**
     * increments the counter when the button is presssed
     * @param event an event that happens (button is clicked)
     * @throws Exception
     */
    public void incrementLabel(ActionEvent event) throws Exception {
//        System.out.println("pressed button");
        pressed++;
        String labeltext = "Button pressed " + pressed + " times!";
        //set the text of the label to the string above
        txtLabel.setText(labeltext);
    }
}
