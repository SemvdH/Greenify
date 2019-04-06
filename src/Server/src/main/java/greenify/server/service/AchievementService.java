package greenify.server.service;

import greenify.server.InputValidator;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This method updates all achievements of a user.
     * @param user the user for whom the achievements change
     */
    public void updateAchievements(User user) {
        achieveGettingStarted(user);
        achieveSocialButterfly(user);
        achieveGreenSaver(user);
        achieveAnimalFriend(user);
        achieveTomDumoulin(user);
        achieveBelowAverage(user);
        userRepository.save(user);
    }

    /**
     * This method changes achiev1 when this is the case.
     * @param user user for whom achiev1 changes
     */
    public void achieveGettingStarted(User user) {
        if (!user.getFootPrintInputs().equals(InputValidator.getDefaultValues())) {
            userService.setAchievement(user.getName(), "Starting off", true);
            userRepository.save(user);
        }
    }

    /**
     * This method changes achiev2 when this is the case.
     * @param user user for whom achiev2 changes
     */
    public void achieveSocialButterfly(User user) {
        if (user.getFriends().size() >= 3) {
            userService.setAchievement(user.getName(), "Social butterfly", true);
            userRepository.save(user);
        }
    }

    /**
     * This method changes achiev3 when this is the case.
     * @param user user for whom achiev3 changes
     */
    public void achieveGreenSaver(User user) {
        if (43-user.getFootPrint() > 38) {
            userService.setAchievement(user.getName(), "Green saver", true);
            userRepository.save(user);
        }
    }

    /**
     * This method changes achiev4 when this is the case.
     * @param user user for whom achiev4 changes
     */
    public void achieveAnimalFriend(User user) {
        if (user.getExtraInputs().get("Vegetarian meal")) {
            userService.setAchievement(user.getName(), "Animal friend", true);
            userRepository.save(user);
        }
    }

    /**
     * This method changes achiev5 when this is the case.
     * @param user user for whom achiev5 changes
     */
    public void achieveTomDumoulin(User user) {
        if (user.getExtraInputs().get("Bike used")) {
            userService.setAchievement(user.getName(), "Tom Dumoulin", true);
            userRepository.save(user);
        }
    }

    /**
     * This method changes achiev6 when this is the case.
     * @param user user for whom achiev6 changes
     */
    public void achieveBelowAverage(User user) {
        if (Integer.parseInt(user.getFootPrintInputs().get("input_footprint_shopping_food_meatfisheggs_default")) < Integer.parseInt("2.4")) {
            userService.setAchievement(user.getName(), "Below average", true);
            userRepository.save(user);
        }
    }

}
