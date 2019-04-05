package greenify.server.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import greenify.server.InputValidator;
import greenify.server.data.model.User;
import greenify.server.data.repository.UserRepository;
import jdk.internal.util.xml.impl.Input;
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

    @TestConfiguration
    static class AchievementServiceConfiguration {
        @Bean
        public AchievementService achievementService() {
            return new AchievementService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private AchievementService achievementService;

    /**
     * setUp method for test.
     */
    @Before
    public void setUp() {
        User alex = new User(1L, "alex", "password");
        when(userRepository.findByName(alex.getName()))
                .thenReturn(alex);
        userService.setInput("alex","input_footprint_shopping_food_otherfood", "9.9");
    }

    @Test
    public void updateAchievementsTest() {
        User alex = userRepository.findByName("alex");
        achievementService.updateAchievements(alex);
        assertEquals(true, userService.getAchievement("alex", "Starting off"));
    }

    @Test
    public void achieveGettingStartedTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveGettingStarted(alex);
        assertEquals(true, userService.getAchievement("alex", "Starting off"));
    }
}
