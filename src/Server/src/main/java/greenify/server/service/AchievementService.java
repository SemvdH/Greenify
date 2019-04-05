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

//    @Autowired
//    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This method updates all achievements of a user.
     * @param user the user for whom the achievements change
     */
    public void updateAchievements(User user) {
        achieveGettingStarted(user);
        System.out.println("\n\nI GOT HERE \n\n");
//        userRepository.save(user);
    }

    /**
     * This method changes achiev1 when this is the case.
     * @param user user for whom achiev1 changes
     */
    public void achieveGettingStarted(User user) {
//        System.out.print("\n\nUSERNAME:" + userRepository.findByName(user.getName()) );
        if (!user.getFootPrintInputs().equals(InputValidator.getDefaultValues())) {
            userService.setAchievement(user.getName(), "Starting off", true);
//            userRepository.save(user);
        }
    }

}
