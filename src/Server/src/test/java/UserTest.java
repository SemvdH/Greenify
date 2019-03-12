import greenify.server.data.model.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    public void setAndGetTest() {
        User user = new User(1L, "greenify", "password");
        User testUser = new User(null, null, null);
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        assertTrue(user.getId().equals(1L));
        assertEquals(user.getName(), "greenify");
        assertEquals(user.getPassword(), "password");
        assertEquals(user, testUser);
    }

    @Test
    public void toStringTest() {
        User user = new User(1L, "greenify", "password");
        assertEquals("User(id=1, name=greenify, password=password)", user.toString());
    }

    @Test
    public void equalsTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "password");
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getPassword(), second.getPassword());
    }

    @Test
    public void hashCodeTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "password");
        assertTrue(first.equals(second) && second.equals(first));
        assertTrue(first.hashCode() == second.hashCode());
    }
}
