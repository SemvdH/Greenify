package greenify.server.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import greenify.common.ApplicationException;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void getFriendEmpty() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "merel", "password");
        assertEquals(first.getFriends(), second.getFriends());
        assertEquals(first.getFriends(), new ArrayList<User>());
    }

    @Test
    public void addFriendTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "merel", "password");
        assertEquals(first.getFriends(), second.getFriends());
        first.addFriend(second);
        ArrayList<User> test = new ArrayList<User>();
        test.add(second);
        assertEquals(first.getFriends(), test);
    }

    @Test
    public void addYourselfTest() {
        User test = new User(1L, "greenify", "password");
        assertEquals(test.getFriends(), new ArrayList<User>());
        assertThrows(ApplicationException.class, () -> {
            test.addFriend(test);
        });
    }


    @Test
    public void friendsToStringTest() {
        User first = new User(1L, "greenify", "password");
        User second = new User(1L, "merel", "password");
        first.addFriend(second);
        assertEquals(first.friendsToString(), "friends=[{name=merel, footprint=0.0}]");
    }
}

}
