import greenify.server.data.model.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    public void setAndGetTest() {
        User user = new User(1L, "greenify", "password", 3);
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        testUser.setVeganMeal(3);
        assertTrue(user.getId().equals(1L));
        assertEquals(user.getName(), "greenify");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getVeganMeal(), 3);

    }

    @Test
    public void equalsMethodTest() {
        User user = new User(1L, "greenify", "password", 3);
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        testUser.setVeganMeal(3);
        assertTrue(user.equals(testUser));
    }

    @Test
    public void toStringTest() {
        User user = new User(1L, "greenify", "password", 3);
        System.out.println("String is " + user.toString());
        assertTrue("User(id=1, name=greenify, password=password, veganMeal=3)".equals(user.toString()));
    }

    @Test
    public void equalsTest() {
        User first = new User(1L, "greenify", "password", 3);
        User second = new User(1L, "greenify", "password", 3);
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getPassword(), second.getPassword());
        assertEquals(first.getVeganMeal(), second.getVeganMeal());
    }

    @Test
    public void hashCodeTest() {
        User first = new User(1L, "greenify", "password", 3);
        User second = new User(1L, "greenify", "password", 3);
        assertTrue(first.equals(second) && second.equals(first));
        assertTrue(first.hashCode() == second.hashCode());
    }
}

