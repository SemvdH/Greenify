package greenify.client.controller;

import greenify.client.rest.UserService;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Class that controls the achievements fxml file (the GUI Screen).
 */
@Controller
public class AchievementsTestController {
    @Autowired
    UserService userService;

    @FXML
    private ImageView achievpic1;

}
