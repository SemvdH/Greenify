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
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * setUp method for test.
     */
    @Before
    public void setUp() {
        User alex = new User(1L, "alex", "password", 0);
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
    }

    @Test
    public void validLoginTest() {
        String name = "alex";
        String password = "password";
        UserDto found = userService.loginUser(name, password);
        assertEquals(found.getName(), name);
    }

    @Test
    public void userRegisterTest() {
        User user = new User(1L, "name", "password", 0);
        UserDto registered = userService.registerUser(user.getName(), user.getPassword());
        assertEquals(registered.getName(), "name");
    }

    @Test
    public void getAllUserTest() {
        assertEquals(userRepository.findAll(), userService.getAllUsers());
    }

    @Test
    public void invalidLoginTest() {
        User user = null;
        assertThrows(ApplicationException.class, () -> {
            userService.loginUser(null, null);
        });
    }

    @Test
    public void addFriendTest() {
        User user = new User(1l,"Merel", "password", 0);
        User friend = new User(2l, "Ellis", "pass", 0);
        userService.registerUser("Merel", "password");
        userService.registerUser("Ellis", "pass");
        assertEquals(user.getFriends(), new ArrayList<User>());
        userService.addFriend(1l,"Merel", "Ëllis");
        List<User> ellis = new ArrayList<User>();
        ((ArrayList) ellis).add(friend);
        assertEquals(user.getFriends(), ellis);
    }

}
