package gogreen.server.service;

import gogreen.common.ApplicationException;
import gogreen.common.UserDTO;
import gogreen.server.data.model.User;
import gogreen.server.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    public UserDTO registerUser(String name, String password) {
        User user = userRepository.findByName(name);
        if (user != null) {
            throw new ApplicationException("User already exists");
        } else {
            user = userRepository.save(new User(null, name, password));
        }
        logger.info("Created user id=" + user.getId() + ", name=" + user.getName());
        return new UserDTO(user.getId(), user.getName());
    }

    public UserDTO login(String name, String password) {
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
}
