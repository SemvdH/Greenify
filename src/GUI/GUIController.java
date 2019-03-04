package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GUIController {
    int pressed;

    //put @FXML to let the fxml file know that it can use this label,
    // ind of the same as with @Test in jUnit tests
    @FXML
    private Label txtLabel;

    public void incrementLabel(ActionEvent event) throws Exception {
//        System.out.println("pressed button");
        pressed++;
        String labeltext = "Button pressed " + pressed + " times!";
        //set the text of the label to the string above
        txtLabel.setText(labeltext);
    }
}
