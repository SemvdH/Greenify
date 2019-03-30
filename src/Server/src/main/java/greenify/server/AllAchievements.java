package greenify.server;

import greenify.server.data.model.Achievement;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class AllAchievements {
    private static final List<Achievement> allAchievements = Arrays.asList(
            new Achievement("Starting off", "You did your first green activity", false),
            new Achievement("Social butterfly", "You added three friends", false)
    );

    /**
     * This method gets default achievements.
     * @return the list of default achievements
     */
    public static List<Achievement> getDefaults() {
        return new ArrayList<>(allAchievements);
    }

}
