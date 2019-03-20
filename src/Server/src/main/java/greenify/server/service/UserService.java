package greenify.server.service;

import greenify.common.ApplicationException;
import greenify.common.UserDto;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//userService class that gets used by the server to handle requests for users
@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    //userRepository to talk with the database

    /**
     * registers the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the registered user
     */
    public UserDto registerUser(String name, String password) {
        User user = userRepository.findByName(name);
        //find the name of the user in the database
        if (user == null) {
            user = new User(null, name, password, 0);
            //if the user isn't already in the database, save it in there
            userRepository.save(user);
        } else {
            throw new ApplicationException("User already exists");
        }
        logger.info("Created user id=" + user.getId() + ", name=" + user.getName());
        //return a transferable user object that has been saved
        return new UserDto(user.getId(), user.getName(), user.getVeganMeal());
    }

    /**
     * logs the user in.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO of the logged in user
     */
    public UserDto loginUser(String name, String password) {
        User user = userRepository.findByName(name);
        //again find the name
        if (user == null) {
            throw new ApplicationException("User does not exist");
            //if it doesn't exist or the password is wrong, throw an exception
        } else {
            if (!user.getPassword().equals(password)) {
                throw new ApplicationException("Wrong password");
            }
        }
        //return a transferable user object that has been logged in
        return new UserDto(user.getId(), user.getName(), user.getVeganMeal());
    }

    /**
     * add vegan meal to the user.
     * @param id the id of the user
     * @param name the name of the user
     */
    public void addVeganMeal(Long id, String name) {
        User user = userRepository.findByName(name);
        int count = user.getVeganMeal();
        //find the user and update their vegetarian meal count
        count++;
        user.setVeganMeal(count);
        //save it to the database
        userRepository.save(user);
        logger.info("Added vegan meal to user(id=" + user.getId()
                + ", name=" + user.getName() + ")");
    }

    /**
     * add vegan meal to the user.
     * @param id the id of the user
     * @param name the name of the user
     */
    public void addFriend(Long id, String name, String friend) {
        User user = userRepository.findByName(name);
        User add = userRepository.findByName(friend);
        user.addFriend(user);
        userRepository.save(user);
        logger.info("Added friend to user(id=" + user.getId()
                + ", name=" + user.getName() + ")");
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}

