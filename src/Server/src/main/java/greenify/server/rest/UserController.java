package greenify.server.rest;

import greenify.common.UserDto;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//class that controls the user with regards to the server and sending data between them
//this class kind of 'redirects' the requests from the client to the server
@RestController
public class UserController {
    @Autowired
    UserService userService;

    //registers a user in the userService
    @RequestMapping("/registerUser")
    public UserDto registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        return userService.registerUser(name, password);
    }

    //logs a user in in the userService
    @RequestMapping("/loginUser")
    public UserDto loginUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    //adds a vegan meal to the user
    @RequestMapping("/addVeganMeal")
    public void addVeganMeal(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "name") String name) {
        userService.addVeganMeal(id, name);
    }
}