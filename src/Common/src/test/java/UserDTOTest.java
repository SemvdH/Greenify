import greenify.common.UserDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDtoTest {
    @Test
    public void setAndGetTest() {
        UserDto user = new UserDto(1L, "greenify");
        UserDto testUser = new UserDto();
        testUser.setId(1L);
        testUser.setName("greenify");
        assertTrue(user.getId() == 1L);
        assertEquals(user.getName(), "greenify");
    }

    @Test
    public void equalsTest() {
        UserDto first = new UserDto(1L, "greenify");
        UserDto second = new UserDto(1L, "greenify");
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
    }
}