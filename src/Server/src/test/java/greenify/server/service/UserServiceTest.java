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
        User user = new User(1L, "user", "password", 0);
        when(userRepository.findByName(user.getName()))
                .thenReturn(user);
        User friend = new User(2L, "friend", "password", 0);
        when(userRepository.findByName(friend.getName()))
                .thenReturn(friend);
    }

    @Test
    public void validLoginTest() {
        String name = "user";
        String password = "password";
        UserDto found = userService.loginUser(name, password);
        assertEquals(found.getName(), name);
    }

    @Test
    public void userRegisterTest() {
        User test = new User(1L, "name", "password", 0);
        UserDto registered = userService.registerUser(test.getName(), test.getPassword());
        assertEquals(registered.getName(), "name");
    }

    @Test
    public void getAllUserTest() {
        assertEquals(userRepository.findAll(), userService.getAllUsers());
    }

    @Test
    public void invalidLoginTest() {
        assertThrows(ApplicationException.class, () -> {
            userService.loginUser(null, null);
        });
    }

    @Test
    public void addFriendTest() {
        userService.addFriend(1L,"user", "friend");
        List<User> test = new ArrayList<User>();
        test.add(userRepository.findByName("friend"));
        assertEquals(userRepository.findByName("user").getFriends(), test);
    }

}
