package greenify.server;

import greenify.server.data.model.Achievement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAchievements {
    private static final List<Achievement> allAchievements = Arrays.asList(
            new Achievement("Starting off", "You did your first green activity", false),
            new Achievement("Social butterfly", "You added three friends", false)
    );

    /**
     * This method gets default achievements.
     * @return the list of default achievements
     */
    public static Map<String, Boolean> getDefaults() {
        Map<String, Boolean> all = new HashMap<>();
        for (Achievement achievement : allAchievements) {
            all.put(achievement.getName(), achievement.isAchieved());
        }
        return all;
    }

}
