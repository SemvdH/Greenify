package greenify.server.service;

import greenify.common.ApplicationException;
import greenify.common.UserDTO;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    /**
     * registers the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the registered user
     */
    public UserDTO registerUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            user = userRepository.save(new User(null, name, password, 0));
        } else {
            throw new ApplicationException("User already exists");
        }
        logger.info("Created user id=" + user.getId() + ", name=" + user.getName());
        return new UserDTO(user.getId(), user.getName());
    }

    /**
     * logs the user in.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the logged in user
     */
    public UserDTO loginUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ApplicationException("User does not exist");
        } else {
            if (!user.getPassword().equals(password)) {
                throw new ApplicationException("Wrong password");
            }
        }
        return new UserDTO(user.getId(), user.getName());
    }

    /**
     * add vegan meal to the user.
     * @param id the id of the user
     * @param name the name of the user
     * @return a userDTO of the user added vegan meal
     */
    public void addVeganMeal(Long id, String name) {
        User user = userRepository.findByName(name);
        int count = user.getVeganMeal();
        count++;
        user.setVeganMeal(count);
        userRepository.save(user);
        logger.info("Added vegan meal to user(id=" + user.getId() + ", name=" + user.getName() + ")");
    }
}

