package greenify.server.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import greenify.server.InputValidator;
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
        assertEquals(false, userService.getAchievement("alex", "Social butterfly"));
    }

    @Test
    public void achieveGettingStartedTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveGettingStarted(alex);
        assertEquals(true, userService.getAchievement("alex", "Starting off"));
        assertEquals(false, userService.getAchievement("alex", "Social butterfly"));

    }

    @Test
    public void achieveSocialButterflyTest() {
        User alex = userRepository.findByName("alex");
        alex.addFriend(new User(2L, "Bubbles", "Bubbles"));
        alex.addFriend(new User(3L, "Cheese", "crackers"));
        alex.addFriend(new User(4L, "Perry", "Doofenshmirtz"));
        achievementService.achieveSocialButterfly(alex);
        assertEquals(true, userService.getAchievement("alex", "Social butterfly"));
    }

    @Test
    public void achieveGreenSaverTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveGreenSaver(alex);
        assertEquals(true, userService.getAchievement("alex", "Green saver"));
    }

    @Test
    public void achieveAnimalFriendTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveAnimalFriend(alex);
        assertEquals(false, userService.getAchievement("alex", "Animal friend"));
    }

    @Test
    public void achieveTomDumoulinTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveTomDumoulin(alex);
        assertEquals(false, userService.getAchievement("alex", "Tom Dumoulin"));
    }

    @Test
    public void achieveLetItShineTest() {
        User alex = userRepository.findByName("alex");
        achievementService.achieveLetItShine(alex);
        assertEquals(false, userService.getAchievement("alex", "Let it shine"));
    }

}
