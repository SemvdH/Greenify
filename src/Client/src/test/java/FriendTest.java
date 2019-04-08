import static org.junit.Assert.assertEquals;

import greenify.client.Friend;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FriendTest {
    @Test
    public void setAndGetTest() {
        Friend test = new Friend("ceren", 10.0);
        test.setFriend("greenify");
        test.setScore(15.0);
        assertEquals(test.getFriend(), "greenify");
        Assertions.assertEquals(test.getScore(), 15.0);
    }
}
