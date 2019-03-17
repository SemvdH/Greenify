import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import greenify.common.ActivityDto;
import org.junit.Test;

public class ActivityDtoTest {
    @Test
    public void setAndGetTest() {
        ActivityDto testActivity = new ActivityDto();
        testActivity.setId(1L);
        testActivity.setName("eatVeganMeal");
        testActivity.setDescription("User adds a vegan meal");
        testActivity.setScore(10);
        ActivityDto activity = new ActivityDto(1L, "eatVeganMeal", "User adds a vegan meal", 10);
        assertTrue(activity.getId() == 1L);
        assertEquals(activity.getName(), "eatVeganMeal");
        assertEquals(activity.getDescription(), "User adds a vegan meal");
        assertEquals(activity.getScore(), 10);
    }

    @Test
    public void equalsTest() {
        ActivityDto first = new ActivityDto(1L, "eatVeganMeal", "User adds a vegan meal", 10);
        ActivityDto second = new ActivityDto(1L, "eatVeganMeal", "User adds a vegan meal", 10);
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getDescription(), second.getDescription());
        assertEquals(first.getScore(), second.getScore());
    }
}