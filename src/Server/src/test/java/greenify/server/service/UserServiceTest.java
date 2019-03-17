package greenify.server.service;

import greenify.common.ApplicationException;
import greenify.common.UserDTO;
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
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
        UserDTO found = userService.loginUser(name, password);
        assertEquals(found.getName(), name);
    }

//    @Test
//    public void addVeganMealTest() {
//        User user = new User(1L, "x", "y", 3);
//        userRepository.save(user);
//        System.out.println(userRepository);
//        userService.addVeganMeal(1L, "x");
//        assertEquals(user.getVeganMeal(), 7);
//    }

    @Test
    public void invalidLoginTest() {
        User user = null;
        assertThrows(ApplicationException.class, () -> {
            userService.loginUser(null, null);
        });
    }
}
