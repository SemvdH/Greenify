package greenify.server.service;

import greenify.common.ApplicationException;
import greenify.common.UserDto;
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
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    CalculatorService calculatorService;

    /**
     * registers the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the registered user
     */
    public UserDto registerUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            user = new User(null, name, password);
            user.setFootPrintInputs(InputValidator.getDefaultValues());
            userRepository.save(user);
        } else {
            throw new ApplicationException("User already exists");
        }
        logger.info("Created user id=" + user.getId() + ", name=" + user.getName());
        return new UserDto(user.getId(), user.getName());
    }

    /**
     * logs the user in.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the logged in user
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
     * @return a userDTO of the logged in user
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
     * The method sets input value.
     * @param name of the user
     * @param inputName is the name of the setting input
     * @param value of the input
     */
    public void setInput(String name, String inputName, String value) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            if (InputValidator.isValidItem(inputName)
                    && InputValidator.isValidItemValue(inputName, value)) {
                user.getFootPrintInputs().put(inputName, value);
                user.setFootPrint(calculatorService.calculateFootprint(user));
            } else {
                throw new ApplicationException("Invalid input");
            }
        }
    }

    /**
     * Gets the input value of an input.
     * @param name of the user
     * @param inputName name of the input
     * @return input value
     */
    public String getInput(String name, String inputName) {
        User user = userRepository.findByName(name);
        if (InputValidator.isValidItem(inputName)) {
            String item = user.getFootPrintInputs().get(inputName);
            return item;
        } else {
            throw new ApplicationException("Invalid input");
        }
    }

    public Float getFootprint(String name) {
        User user = userRepository.findByName(name);
        return calculatorService.calculateFootprint(user);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}

