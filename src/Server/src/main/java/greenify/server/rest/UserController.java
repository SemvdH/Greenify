package greenify.server.rest;

import greenify.common.UserDto;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//class that controls the user with regards to the server and sending data between them
//this class kind of 'redirects' the requests from the client to the server
@RestController
public class UserController {
    @Autowired
    UserService userService;

    //registers a user in the userService
    @RequestMapping("/registerUser")
    //requestMapping is for the communication (GET, POST, PUT requests)
    //as with Web and Database Technology
    public UserDto registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        //the requestParams are the parameters that are sent with the request
        //so in this case that it wants to register with the name and password
        return userService.registerUser(name, password);
    }

    //logs a user in in the userService
    @RequestMapping("/loginUser")
    public UserDto loginUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    /**
     * adds a vegetarian meal to the user.
     * @param id the id of the user
     * @param name thr username of the user
     */
    @RequestMapping("/addVeganMeal")
    public void addVeganMeal(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "name") String name) {
        //here the requestParams are the id and name, because that is needed for the
        //addVeganMeal method of the userService
        userService.addVeganMeal(id, name);
    }

    /**
     * adds a friend to the user.
     * @param id the id of the user
     * @param name thr username of the user
     */
    @RequestMapping("/addVeganMeal")
    public void addVeganMeal(@RequestParam(value = "id") Long id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "friend") String friend) {
        //here the requestParams are the id and name of the user and the name of the friend,
        // because that is needed for the addFriendmethod of the userService
        userService.addFriend(id, name, friend);
    }
}