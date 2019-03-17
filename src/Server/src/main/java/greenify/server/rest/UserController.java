package greenify.server.rest;

import greenify.common.UserDto;
import greenify.server.data.model.User;
import greenify.server.service.UserService;
import greenify.server.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/registerUser")
    public UserDto registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        return userService.registerUser(name, password);
    }

    @RequestMapping("/loginUser")
    public UserDto loginUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    @RequestMapping("/addVeganMeal")
    public void addVeganMeal(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "name") String name) {
        userService.addVeganMeal(id, name);
    }

    @GetMapping("/getUsername")
    public void getUsername(@RequestParam(value = "id") Long id) {
        userService.getUsername(id);
    
    
    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}