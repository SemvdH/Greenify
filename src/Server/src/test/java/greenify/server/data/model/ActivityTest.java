package greenify.server.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ActivityTest {
    @Test
    public void setAndGetTest() {
        Activity testActivity = new Activity(0, null, null, 0);
        testActivity.setId(1L);
        testActivity.setName("Vegetarian");
        testActivity.setDescription("Eating");
        testActivity.setScore(100);
        Activity activity = new Activity(1L, "Vegetarian", "Eating", 100);
        assertTrue(activity.getId().equals(1L));
        assertEquals(activity.getName(), "Vegetarian");
        assertEquals(activity.getDescription(), "Eating");
        assertEquals(activity.getScore(), 100);
        assertEquals(activity, testActivity);
    }

    @Test
    public void toStringTest() {
        Activity activity = new Activity(1, "Solar panels", "Installed", 10000);
        assertEquals("Activity(id=1, name=Solar panels, "
                + "description=Installed, score=10000)", activity.toString());
    }

    @Test
    public void equalsTest() {
        Activity first = new Activity(1, "Solar panels", "Installed", 10000);
        Activity second = new Activity(1, "Solar panels", "Installed", 10000);
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getScore(), second.getScore());
        assertTrue(first.equals(second));
    }

    @Test
    public void hashCodeTest() {
        Activity first = new Activity(1, "Solar panels", "Installed", 10000);
        Activity second = new Activity(1, "Solar panels", "Installed", 10000);
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
