package greenify.server.rest;

import greenify.common.UserDTO;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/login")
    public UserDTO login(@RequestParam(value = "name") String name,
                         @RequestParam(value = "password") String password) {
        return userService.login(name, password);
    }
}