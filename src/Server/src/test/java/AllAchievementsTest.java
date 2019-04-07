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
        List<Achievement> all = new ArrayList<Achievement>() {{
                add(new Achievement(
                    "Starting off", "You did your first green activity", false));
                add(new Achievement(
                    "Social butterfly", "You added three friends", false));
                add(new Achievement(
                    "Green saver", "You saved * of CO2", false));
                add(new Achievement(
                    "Animal friend", "You have eaten 10 vegetarian meals", false));
                add(new Achievement(
                    "Tom Dumoulin", "You have biked * km", false));
                add(new Achievement(
                    "Let it shine", "You installed solar panels", false));
            }
        };
        assertEquals(all.size(), AllAchievements.getDefaults().size());
    }
}
