import static org.junit.Assert.assertEquals;

import greenify.common.UserDto;
import org.junit.Test;

public class UserDtoTest {

    @Test
    public void setAndGetTest() {
        UserDto testUser = new UserDto();
        testUser.setId(1L);
        testUser.setName("greenify");
        UserDto user = new UserDto(1L, "greenify");
        assertEquals(1L, (long) user.getId());
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
