package greenify.server.data.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void setAndGetTest() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        User user = new User(1L, "greenify", "password");
        assertEquals(1L, (long) user.getId());
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
        User first = new User(2L, "greenify", "12345");
        User second = new User(2L, "greenify", "12345");
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getPassword(), second.getPassword());
        assertEquals(first, second);
    }

    @Test
    public void equalsDifferentId() {
        User first = new User(1L, "greenify", "password");
        User second = new User(2L, "greenify", "password");
        assertNotEquals(first, second);
    }

    @Test
    public void equalsDifferentName() {
        User first = new User(5L, "greenify", "password");
        User second = new User(5L, "hello", "password");
        assertNotEquals(first, second);
    }

    @Test
    public void equalsDifferentPassword() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "hi");
        assertNotEquals(first, second);
    }

    @Test
    public void notEqualsTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(2L, "greenify", "password");
        assertNotEquals(first, second);
    }

    @Test
    public void instanceOfTest() {
        User first = new User();
        Object second = new Object();
        assertNotEquals(first, second);
    }

    @Test
    public void hashCodeTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "password");
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

}
