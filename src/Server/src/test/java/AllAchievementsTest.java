import static org.junit.Assert.assertEquals;

import greenify.server.AllAchievements;
import greenify.server.data.model.Achievement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AllAchievementsTest {

    @Test
    void isValidAchievementTest() {
        new AllAchievements();
        assertEquals(true, AllAchievements.isValidAchievement(
                "Starting off"));
        assertEquals(false, AllAchievements.isValidAchievement("test"));
    }

    @Test
    void getDefaultsTest() {
        assertEquals(6, AllAchievements.getDefaults().size());
    }
}
