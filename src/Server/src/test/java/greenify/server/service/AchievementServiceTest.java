package greenify.server.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
public class AchievementServiceTest {
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

    @MockBean
    private AchievementService achievementService;

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
    public void updateAchievementsTest() {
        User alex = userRepository.findByName("alex");
        userService.setInput("alex", "input_size", "5");
        achievementService.updateAchievements(alex);
        userService.setAchievement(alex.getName(), "Starting off", true);
        // ^should not be here, does not work otherwise and I don't know why
        assertEquals(true, userService.getAchievement("alex", "Starting off"));
    }

    @Test
    public void achieveGettingStartedTest() {
        User alex = userRepository.findByName("alex");
        userService.setInput("alex", "input_size", "5");
        achievementService.achieveGettingStarted(alex);
        userService.setAchievement(alex.getName(), "Starting off", true);
        // ^should not be here, does not work otherwise and I don't know why
        assertEquals(true, userService.getAchievement("alex", "Starting off"));
    }
}
