import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import greenify.common.UserDto;
import org.junit.Test;

public class UserDtoTest {
    @Test
    public void setAndGetTest() {
        UserDto testUser = new UserDto();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setVeganMeal(0);
        UserDto user = new UserDto(1L, "greenify", 0);
        assertTrue(user.getId() == 1L);
        assertEquals(user.getName(), "greenify");
        assertTrue(user.getVeganMeal() == 0);
    }

    @Test
    public void equalsTest() {
        UserDto first = new UserDto(1L, "greenify", 0);
        UserDto second = new UserDto(1L, "greenify", 0);
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
    }
}