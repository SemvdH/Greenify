package greenify.server.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class AchievementTest {
    private Achievement achievement = new Achievement("SavedCO2",
            "You saved 100 cow farts of CO2!", true);
    private Achievement other = new Achievement("SavedCO2",
            "You saved 100 cow farts of CO2!", true);

    @Test
    public void getAndSetTest() {
        Achievement testAchievement = new Achievement();
        testAchievement.setName("SavedCO2");
        testAchievement.setDescription("You saved 100 cow farts of CO2!");
        testAchievement.setAchieved(true);
        assertEquals("SavedCO2", achievement.getName());
        assertEquals("You saved 100 cow farts of CO2!", achievement.getDescription());
        assertTrue(achievement.isAchieved());
        assertEquals(achievement, testAchievement);
    }

    @Test
    public void toStringTest() {
        assertEquals("Achievement(name=SavedCO2, "
                + "description=You saved 100 cow farts of CO2!, achieved=true)",
                achievement.toString());
    }

    @Test
    void equalsTest() {
        assertEquals(achievement.getName(), other.getName());
        assertEquals(achievement.getDescription(), other.getDescription());
        assertEquals(achievement.isAchieved(), other.isAchieved());
        assertEquals(achievement, other);
    }

    @Test
    void notEqualsTest() {
        Achievement test = new Achievement("Starting off",
                "You did your first green activity", false);
        assertFalse(achievement.equals(test));
    }

    @Test
    void equalsNullTest() {
        assertFalse(achievement.equals(null));
    }
    @Test
    void hashCodeTest() {
        assertEquals(achievement, other);
        assertEquals(achievement.hashCode(), other.hashCode());
    }
}
