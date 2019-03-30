package greenify.server.service;

import greenify.common.ApplicationException;
import greenify.common.UserDto;
import greenify.server.AllAchievements;
import greenify.server.InputValidator;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserService {
    @Autowired
    CalculatorService calculatorService;

    @Autowired
    AchievementService achievementService;

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * This method registers the user.
     * @param name name of the user
     * @param password password of the user
     * @return the userDto of the user
     */
    public UserDto registerUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            user = new User(null, name, password);
            user.setFootPrintInputs(InputValidator.getDefaultValues());
            Float footprint = calculatorService.calculateFootprint(user);
            user.setFootPrint(footprint);
            user.setAchievements(AllAchievements.getDefaults());
            userRepository.save(user);
        } else {
            throw new ApplicationException("User already exists");
        }
        logger.info("Created user id=" + user.getId() + ", name=" + user.getName());
        return new UserDto(user.getId(), user.getName());
    }

    /**
     * This method logs in the user.
     * @param name name of the user
     * @param password password of the user
     * @return the userDto of the user
     */
    public UserDto loginUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            if (!user.getPassword().equals(password)) {
                throw new ApplicationException("Wrong password");
            }
        }
        return new UserDto(user.getId(), user.getName());
    }

    /**
<<<<<<< HEAD
     * Adds a friend to the friendlist of the user.
     * @param name the username of the user
     * @param friend the name of the friend you want to add.
     */
    public void addFriend(String name, String friend) {
        User user = userRepository.findByName(name);
        User add = userRepository.findByName(friend);
        if (add == null) {
            throw new ApplicationException("User does not exist");
        }
        user.addFriend(add);
    }

    /**
     * Returns the friendlist of the user in JSON.
     * @param name the username of the user
     * @return a userDTO of the logged in user
     */
    public String getLeaderboard(String name) {
        User user = userRepository.findByName(name);
        return user.friendsToString();
    }

    /**
     * This method sets input for a user.
     * @param name name of the user
     * @param inputName name of the input of the user
     * @param value value of the input
     */
    public void setInput(String name, String inputName, String value) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            if (InputValidator.isValidItem(inputName)
                    && InputValidator.isValidItemValue(inputName, value)) {
                user.getFootPrintInputs().put(inputName, value);
                userRepository.save(user);
                user.setFootPrint(calculatorService.calculateFootprint(user));
                achievementService.updateAchievements(user);
                userRepository.save(user);
            } else {
                throw new ApplicationException("Invalid input");
            }
        }
    }

    /**
     * This method gets the input value of an input.
     * @param name of the user
     * @param inputName name of the input
     * @return input value
     */
    public String getInput(String name, String inputName) {
        User user = userRepository.findByName(name);
        if (InputValidator.isValidItem(inputName)) {
            return user.getFootPrintInputs().get(inputName);
        } else {
            throw new ApplicationException("Invalid input");
        }
    }

    /**
     * This method gets the footprint of a user.
     * @param name name of the user
     * @return footprint of the user
     */
    public Float getFootprint(String name) {
        User user = userRepository.findByName(name);
        user.setFootPrint(calculatorService.calculateFootprint(user));
        userRepository.save(user);
        return user.getFootPrint();
    }

    /**
     * This methods sets a achievement
     * @param name name of the user
     * @param achievement name of the achievement
     * @param achieved (not) achieved
     */
    public void setAchievement(String name, String achievement, Boolean achieved) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            if (AllAchievements.isValidAchievement(achievement)) {
                user.getAchievements().put(achievement, achieved);
                userRepository.save(user);
            } else {
                throw new ApplicationException("Invalid achievement");
            }
        }
    }

    /**
     * This method gets whether the achievement is achieved
     * @param name of the user
     * @param achievement name of the achievement
     * @return (not) achieved
     */
    public Boolean getAchievement(String name, String achievement) {
        User user = userRepository.findByName(name);
        if (AllAchievements.isValidAchievement(achievement)) {
            return user.getAchievements().get(achievement);
        } else {
            throw new ApplicationException("Invalid achievement");
        }
    }

    /**
     * This method gets a JSON of XML with all users.
     * @return JSON/XML of all users
     */
    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
