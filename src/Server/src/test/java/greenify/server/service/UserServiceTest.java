package greenify.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import greenify.common.ApplicationException;
import greenify.common.UserDto;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
public class UserServiceTest {
    @TestConfiguration
    static class UserServiceConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CalculatorService calculatorService;

    /**
     * setUp method for test.
     */
    @Before
    public void setUp() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        User lola = new User(2L, "lola", "password");
        when(userRepository.findByName(lola.getName()))
                .thenReturn(lola);
    }

    @Test
    public void validLoginTest() {
        String name = "alex";
        String password = "password";
        UserDto found = userService.loginUser(name, password);
        assertEquals(found.getName(), name);
    }

    @Test
    public void loginExceptionTest() {
        assertThrows(ApplicationException.class, () -> userService.loginUser("alex", "greenify"));
    }

    @Test
    public void userRegisterTest() {
        User user = new User(1L, "name", "password");
        UserDto registered = userService.registerUser(user.getName(), user.getPassword());
        assertEquals(registered.getName(), "name");
    }

    @Test
    public void registerExceptionTest() {
        assertThrows(ApplicationException.class, () ->
                userService.registerUser("alex", "password"));
    }

    @Test
    public void setInputTest() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        userService.setInput("alex", "food_grains", "6.5");
        assertEquals("6.5", alex.getFootPrintInputs().get("food_grains"));
    }

    @Test
    public void setInputNullTest() {
        assertThrows(ApplicationException.class, () -> userService.setInput(null, "hello", "5.5"));
    }

    @Test
    public void setInputApplicationTestItem() {
        assertThrows(ApplicationException.class, () -> userService.setInput("tom", "hello", "3.5"));
    }

    @Test
    public void setInputApplicationTestValue() {
        assertThrows(ApplicationException.class, () ->
                userService.setInput("tom", "transportation_num_vehicles", "5.5"));
    }

    @Test
    public void setInputFootprintTest() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        when(calculatorService.calculateFootprint(alex))
                .thenReturn(15f);
        userService.setInput("alex", "food_grains", "6.5");
        assertEquals(15f, alex.getFootPrint(), 0.0);
    }

    @Test
    public void getInputTest() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        userService.setInput("alex", "food_grains", "6.5");
        assertEquals("6.5", userService.getInput("alex", "food_grains"));
    }

    @Test
    public void getInputExceptionTest() {
        assertThrows(ApplicationException.class, () -> userService.getInput("alex", "hello"));
    }

    @Test
    public void getFootprintTest() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        when(calculatorService.calculateFootprint(alex))
                .thenReturn(15f);
        userService.setInput("alex", "food_grains", "6.5");
        assertEquals(15f, userService.getFootprint("alex"), 0.0);
    }

    @Test
    public void getAllUserTest() {
        assertEquals(userRepository.findAll(), userService.getAllUsers());
    }

    @Test
    public void invalidLoginTest() {
        assertThrows(ApplicationException.class, () -> userService.loginUser(null, null));
    }

    @Test
    public void addFriendTest() {
        User alex = userRepository.findByName("alex");
        User lola = userRepository.findByName("lola");
        assertEquals(lola.getFriends(), alex.getFriends());
        userService.addFriend("alex", "lola");
        ArrayList<User> test = new ArrayList<User>();
        test.add(lola);
        assertEquals(alex.getFriends(), test);
    }

    @Test
    public void leaderboardTest() {
        User alex = userRepository.findByName("alex");
        User lola = userRepository.findByName("lola");
        userService.addFriend("alex", "lola");
        assertEquals(userService.getLeaderboard("alex"), "friends=[{name=lola, footprint=0.0}]");

    }
}
