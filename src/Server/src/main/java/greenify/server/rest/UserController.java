package greenify.server.rest;

import greenify.common.UserDTO;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/registerUser")
    public UserDTO registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        return userService.registerUser(name, password);
    }

    @RequestMapping("/loginUser")
    public UserDTO loginUser(@RequestParam(value = "name") String name,
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
    }
}