package greenify.server.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class UserTest {
    @Test
    public void setAndGetTest() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        User user = new User(1L, "greenify", "password");
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
        assertTrue(first.equals(second));
    }

    @Test
    public void equalsDifferentId() {
        User first = new User(1L, "greenify", "password");
        User second = new User(2L, "greenify", "password");
        assertFalse(first.equals(second));
    }

    @Test
    public void equalsDifferentName() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "hello", "password");
        assertFalse(first.equals(second));
    }

    @Test
    public void equalsDifferentPassword() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "hi");
        assertFalse(first.equals(second));
    }

    @Test
    public void notEqualsTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(2L, "greenify", "password");
        assertFalse(first.equals(second));
    }

    @Test
    public void instanceOfTest() {
        User first = new User();
        Object second = new Object();
        assertFalse(first.equals(second));
    }

    @Test
    public void hashCodeTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "greenify", "password");
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}

