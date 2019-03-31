package greenify.server.rest;

import greenify.common.UserDto;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private
        UserService userService;

    /**
     * This method registers the user.
     * @param name name of the user
     * @param password password of the user
     * @return the userDto of the user
     */
    @RequestMapping("/registerUser")
    public UserDto registerUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "password") String password) {
        return userService.registerUser(name, password);
    }

    /**
     * This method logs in the user.
     * @param name name of the user
     * @param password password of the user
     * @return the userDto of the user
     */
    @RequestMapping("/loginUser")
    public UserDto loginUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password) {
        return userService.loginUser(name, password);
    }

    /**
     * This method sets input for a user.
     * @param name name of the user
     * @param inputName name of the input of the user
     * @param value value of the input
     */
    @RequestMapping("/setInput")
    public void setInput(@RequestParam(value = "name") String name,
                         @RequestParam(value = "inputName") String inputName,
                         @RequestParam(value = "value") String value) {
        userService.setInput(name, inputName, value);
    }

    /**
     * This method gets input for a user.
     * @param name name of the user
     * @param inputName name of the input of the user
     */
    @RequestMapping("/getInput")
    public void getInput(@RequestParam(value = "name") String name,
                         @RequestParam(value = "inputName") String inputName) {
        userService.getInput(name, inputName);
    }

    /**
     * This method gets footprint for a user.
     * @param name name of the user
     */
    @RequestMapping("/getFootprint")
    public Float getFootprint(@RequestParam(value = "name") String name) {
        Float footprint = userService.getFootprint(name);
        return footprint;
    }

    /**
     * This method saves footprint for a user.
     * @param name name of the user
     */
    @RequestMapping("/saveFootprint")
    public Float saveFootprint(@RequestParam(value = "name") String name) {
        Float footprint = userService.saveFootprint(name);
        return footprint;
    }

    /**
     * This method gets friend list for a user.
     * @param name name of the user
     */
    @RequestMapping("/getFriends")
    public List<String> getFriendNames(@RequestParam(value = "name") String name) {
        List<String> friends = userService.getFriends(name);
        return friends;
    }

    /**
     * This method gets the list of all users.
     */
    @RequestMapping("/getAllUsers")
    public List<String> getAllUsers() {
        List<String> users = userService.getAllUsers();
        return users;
    }

    /**
     * This method adds friend for a user.
     * @param name name of the user
     *
     */
    @RequestMapping("/addFriend")
    public void addFriend(@RequestParam(value = "name") String name,
                              @RequestParam(value = "friend") String friend) {
        userService.addFriend(name, friend);
    }
}
