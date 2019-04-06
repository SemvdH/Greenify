package greenify.server;

import greenify.server.data.model.Achievement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAchievements {
    private static final List<Achievement> allAchievements = Arrays.asList(
            new Achievement("Starting off", "You did your first green activity", false),
            new Achievement("Social butterfly", "You added three friends", false),
            new Achievement("Green saver", "You saved * of CO2", false),
            new Achievement("Animal friend", "You have eaten 10 vegetarian meals", false),
            new Achievement("Tom Dumoulin", "You have biked * km", false),
            new Achievement("Below average!", "You spend less CO2 than average on everything", false),
            new Achievement("", "", false)
    );

    /**
     * The method checks whether the achievement name is valid or not.
     * @param achievementName the name of the achievement
     * @return true or false
     */
    public static Boolean isValidAchievement(String achievementName) {
        return allAchievements.stream().anyMatch(i -> i.getName().equals(achievementName));
    }

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
