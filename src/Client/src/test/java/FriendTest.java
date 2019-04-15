import static org.junit.Assert.assertEquals;

import greenify.client.Friend;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FriendTest {
    @Test
    public void setAndGetTest() {
        Friend test = new Friend(1,"ceren", 10.0);
        test.setPlace(1);
        test.setFriend("greenify");
        test.setScore(15.0);
        Assertions.assertEquals(test.getPlace(), 1);
        assertEquals(test.getFriend(), "greenify");
        Assertions.assertEquals(test.getScore(), 15.0);
    }
}
