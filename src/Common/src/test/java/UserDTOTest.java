import greenify.common.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDTOTest {
    @Test
    public void setAndGetTest() {
        UserDTO user = new UserDTO(1L, "greenify");
        UserDTO testUser = new UserDTO();
        testUser.setId(1L);
        testUser.setName("greenify");
        assertTrue(user.getId() == 1L);
        assertEquals(user.getName(), "greenify");
    }

    @Test
    public void equalsTest() {
        UserDTO first = new UserDTO(1L, "greenify");
        UserDTO second = new UserDTO(1L, "greenify");
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
    }
}