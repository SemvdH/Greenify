package greenify.server;

import greenify.server.data.model.Achievement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        List<Achievement> all = new ArrayList<Achievement>() {{
            add(new Achievement("Starting off", "You did your first green activity", false));
            add(new Achievement("Social butterfly", "You added three friends", false));
        }};
        assertEquals(all.size(), AllAchievements.getDefaults().size());
    }
}
