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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * Adds a friend to the friendlist of the user.
     * @param name the username of the user
     * @param friend the name of the friend you want to add.
     * @throws ApplicationException if the user is not in the database.
     */
    public void addFriend(String name, String friend) {
        User user = userRepository.findByName(name);
        User add = userRepository.findByName(friend);
        if (add == null ) {
            throw new ApplicationException("User does not exist");
        }
        user.addFriend(add);
        userRepository.save(user);
    }

    /**
     * Removes a friend from the friendlist of the user.
     * @param name the username of the user
     * @param friend the name of the friend you want to remove.
     * @throws ApplicationException if the user is not in the database.
     */
    public void removeFriend(String name, String friend) {
        User user = userRepository.findByName(name);
        User remove = userRepository.findByName(friend);
        if (remove == null ) {
            throw new ApplicationException("User does not exist");
        }
        user.removeFriend(remove);
        userRepository.save(user);
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
                achievementService.updateAchievements(user);
            } else {
                throw new ApplicationException("Invalid input");
            }
        }
    }

    /**
     * This method sets extra input for a user.
     * @param name name of the user
     * @param inputName name of the input of the user
     * @param value value of the input
     */
    public void setExtraInput(String name, String inputName, Boolean value) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            user.getExtraInputs().put(inputName, value);
            userRepository.save(user);
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
     * This method gets the map of the inputs.
     * @param name of the user
     * @return input map
     */
    public Map<String, String> getInputMap(String name) {
        User user = userRepository.findByName(name);
        return user.getFootPrintInputs();
    }

    /**
     * This method gets the map of extra inputs.
     * @param name of the user
     * @return extra input map
     */
    public Map<String, Boolean> getExtraInputMap(String name) {
        User user = userRepository.findByName(name);
        return user.getExtraInputs();
    }

    /**
     * This method saves the footprint of a user.
     * @param name name of the user
     * @return footprint of the user
     */
    public Float saveFootprint(String name) {
        User user = userRepository.findByName(name);
        user.setFootPrint(calculatorService.calculateFootprint(user));
        userRepository.save(user);
        return user.getFootPrint();
    }

    /**
     * This method saves the first footprint of a user.
     * @param name name of the user
     * @return footprint of the user
     */
    public Float saveFirstFootprint(String name) {
        User user = userRepository.findByName(name);
        user.setFirstFootprint(calculatorService.calculateFootprint(user));
        userRepository.save(user);
        return user.getFootPrint();
    }

    /**
     * This method gets the footprint of a user.
     * @param name name of the user
     * @return footprint of the user
     */
    public Float getFootprint(String name) {
        User user = userRepository.findByName(name);
        return user.getFootPrint();
    }

    /**
     * This method gets the first footprint of a user.
     * @param name name of the user
     * @return first footprint of the user
     */
    public Float getFirstFootprint(String name) {
        User user = userRepository.findByName(name);
        return user.getFirstFootprint();
    }

    /**
     * This method gets the friends of a user.
     * @param name name of the user
     * @return list of the friends
     */
    public List<String> getFriends(String name) {
        List<String> result = new ArrayList<>();
        User user = userRepository.findByName(name);
        List<User> friends = user.getFriends();
        for (User person : friends) {
            result.add(person.getName());
        }
        return result;
    }

    /**
     * This methods sets a achievement.
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
     * This method gets whether the achievement is achieved.
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
     * This method gets all achievements of a user.
     * @param name name of the user
     * @return map with all achievements of a user
     */
    public Map<String, Boolean> getAchievements(String name) {
        User user = userRepository.findByName(name);
        return user.getAchievements();
    }

    /**
     * This method gets the list of all users.
     * @return list of all users
     */
    public List<String> getAllUsers() {
        List<String> result = new ArrayList<>();
        Iterable<User>  allUsers = userRepository.findAll();
        for (User person : allUsers) {
            result.add(person.getName());
        }
        return result;
    }
}
