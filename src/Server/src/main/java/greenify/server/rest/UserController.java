package greenify.server.rest;

import greenify.common.UserDto;
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
    public UserDto registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        return userService.registerUser(name, password);
    }

    @RequestMapping("/loginUser")
    public UserDto loginUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    @RequestMapping("/setInput")
    public void setInput(@RequestParam(value = "name") String name,
                         @RequestParam(value = "inputName") String inputName,
                         @RequestParam(value = "value") String value) {
        userService.setInput(name, inputName, value);
    }
}