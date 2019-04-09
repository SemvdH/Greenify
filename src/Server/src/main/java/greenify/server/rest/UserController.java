package greenify.server.rest;

import greenify.common.UserDto;
import greenify.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
     * This method sets extra input for a user.
     * @param name name of the user
     * @param inputName name of the input of the user
     * @param value value of the input
     */
    @RequestMapping("/setExtraInput")
    public void setExtraInput(@RequestParam(value = "name") String name,
                         @RequestParam(value = "inputName") String inputName,
                         @RequestParam(value = "value") String value) {
        userService.setExtraInput(name, inputName, value);
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
     * This method saves first footprint for a user.
     * @param name name of the user
     */
    @RequestMapping("/saveFirstFootprint")
    public Float saveFirstFootprint(@RequestParam(value = "name") String name) {
        Float footprint = userService.saveFirstFootprint(name);
        return footprint;
    }

    /**
     * This method gets first footprint for a user.
     * @param name name of the user
     */
    @RequestMapping("/getFirst")
    public Float getFirstFootprint(@RequestParam(value = "name") String name) {
        System.out.println("hello");
        Float footprint = userService.getFirstFootprint(name);
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
     * This method gets the input map of the user.
     */
    @RequestMapping("/getInputs")
    public Map<String, String> getInputs(@RequestParam(value = "name") String name) {
        return userService.getInputMap(name);
    }

    /**
     * This method gets the extra inputs map of the user.
     */
    @RequestMapping("/getExtraInputs")
    public Map<String, String> getExtraInputs(@RequestParam(value = "name") String name) {
        return userService.getExtraInputMap(name);
    }

    /**
     * This method adds a friend to a user.
     * @param name name of the user
     * @param friend the name of the user you want to add as a friend.
     */
    @RequestMapping("/addFriend")
    public void addFriend(@RequestParam(value = "name") String name,
                          @RequestParam(value = "friend") String friend) {
        userService.addFriend(name, friend);
    }

    /**
     * This method removes a friend from a user.
     * @param name name of the user
     * @param friend name of the friend you want to remove
     */
    @RequestMapping("/removeFriend")
    public void removeFriend(@RequestParam(value = "name") String name,
                             @RequestParam(value = "friend") String friend) {
        userService.removeFriend(name, friend);
    }

    /**
     * This method gets all achievements of a user.
     * @param name name of the user
     * @return map of all achievements of the user
     */
    @RequestMapping("/getAchievements")
    public Map<String, Boolean> getAchievements(@RequestParam(value = "name") String name) {
        return userService.getAchievements(name);
    }

    /**
     * This method gets the result for schema.
     * @param name name of the user
     * @return map of all results of the user
     */
    @RequestMapping("/getResults")
    public Map<String, String> getResults(@RequestParam(value = "name") String name) {
        return userService.getResults(name);
    }

    /**
     * This method deletes a user from the database.
     * @param name name of the user
     */
    @RequestMapping("/deleteAccount")
    public void removeFriend(@RequestParam(value = "name") String name) {
        userService.deleteAccount(name);
    }
}

