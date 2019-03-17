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
    public void getAllUserTest() {
        assertEquals(userRepository.findAll(), userService.getAllUsers());
    }

    //    @Test
    //    public void validRegisterTest() {
    //        String name = "new";
    //        String password = "user";
    //        if(userRepository.findByName(name) == null) {
    //            UserDto found = userService.registerUser(name, password);
    //            assertEquals(found.getName(), name);
    //        }
    //    }

    //        @Test
    //        public void addVeganMealTest() {
    //            User user = new User(1L, "name", "password", 0);
    //            User user = userRepository.findByName("x");
    //            int veganMeal = user.getVeganMeal();
    //            userService.addVeganMeal(user.getId(), user.getName());
    //            assertEquals(user.getVeganMeal(), veganMeal++);
    //        }

    @Test
    public void invalidLoginTest() {
        User user = null;
        assertThrows(ApplicationException.class, () -> {
            userService.loginUser(null, null);
        });
    }
}
