package greenify.server.data.model;

import static greenify.server.data.model.User.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import greenify.common.ApplicationException;
import greenify.server.Application;
import org.junit.Test;

import java.util.ArrayList;

public class UserTest {
    @Test
    public void setAndGetTest() {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("greenify");
        testUser.setPassword("password");
        testUser.setVeganMeal(3);
        User user = new User(1L, "greenify", "password", 3);
        assertTrue(user.getId().equals(1L));
        assertEquals(user.getName(), "greenify");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getVeganMeal(), 3);
        assertEquals(user, testUser);
        assertEquals(user.getFriends(), new ArrayList<User>());
    }

    @Test
    public void toStringTest() {
        User user = new User(1L, "greenify", "password", 3);
        assertEquals("User(id=1, name=greenify, password=password, veganMeal=3)", user.toString());
    }

    @Test
    public void equalsNullTest(){
        User first = new User(1L, "greenify", "password", 0);
        User second = null;
        assertNotEquals(first, second);
    }

    @Test
    public void equalsTest() {
        User first = new User(1L, "greenify", "password", 3);
        User second = new User(1L, "greenify", "password", 3);
        assertEquals(first.getId(), second.getId());
        assertEquals(first.getName(), second.getName());
        assertEquals(first.getPassword(), second.getPassword());
        assertEquals(first.getVeganMeal(), second.getVeganMeal());
        assertTrue(first.equals(second));
    }

    @Test
    public void notEqualsTest() {
        User first = new User(1L, "greenify", "password", 3);
        User second = new User(1L, "greenify", "password", 7);
        assertFalse(first.equals(second));
    }

    @Test
    public void sameEqualsTest(){
        User user = new User(6l, "Merel", "password", 0);
        assertEquals(user, user);
    }

    @Test
    public void instanceOfTest() {
        User first = new User();
        Object second = new Object();
        assertFalse(first.equals(second));
    }

    @Test
    public void hashCodeTest() {
        User first = new User(1L, "greenify", "password", 3);
        User second = new User(1L, "greenify", "password", 3);
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void addFriendTest(){
        User user = new User(1l, "user", "friends", 0);
        User friend = new User(2l, "friend", "friends", 0);
        assertEquals(user.getFriends(), new ArrayList<User>());
        user.addFriend(friend);
        ArrayList<User> list = new ArrayList<User>();
        list.add(friend);
        assertEquals(user.getFriends(), list);
    }

    @Test
    public void addYourselfTest(){
        User user = new User(1l, "user", "friends", 0);
        assertThrows(ApplicationException.class, () -> {
            user.addFriend(user);
        });
        assertEquals(user.getFriends(), new ArrayList<User>());
    }

    @Test
    public void JsonTest(){
        User user = new User(1l, "user", "friends", 0);
        User friend = new User(2l, "friend", "friends", 0);
        assertEquals(user.getFriends(), new ArrayList<User>());
        user.addFriend(friend);
        ArrayList<User> list = new ArrayList<User>();
        list.add(friend);
        assertEquals(user.getFriends(), list);
        System.out.println(user.toString());
    }

    @Test
    public void friendsToStringTest(){
        User user = new User(4l, "user", "pass", 0);
        User friend = new User (5l, "friend", "pass", 0);
        user.addFriend(friend);
        System.out.println(user.friendsToString());
    }
}

