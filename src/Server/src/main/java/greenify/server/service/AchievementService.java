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

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This method updates all achievements of a user.
     * @param user the user for whom the achievements change
     */
    public void updateAchievements(User user) {
        achieveGettingStarted(user);
        achieveSocialButterfly(user);
    }

    /**
     * This method makes sure the user gets an achievement
     * upon calculating their footprint for the first time.
     * @param user user for whom achiev1 changes
     */
    public void achieveGettingStarted(User user) {
        if (!user.getFootPrintInputs().equals(InputValidator.getDefaultValues())) {
            userService.setAchievement(user.getName(), "Starting off", true);
        }
    }

    /**
     * This method makes sure the user gets an achievement
     * when they have added three friends.
     * @param user user for whom achiev2 changes
     */
    public void achieveSocialButterfly(User user) {
        if (user.getFriends().size() == 3) {
            userService.setAchievement(user.getName(), "Social butterfly", true);
        }
    }

}
