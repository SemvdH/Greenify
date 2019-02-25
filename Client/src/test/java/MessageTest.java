import hello.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MessageTest {
    @Test
    void testGets() {
        Message message = new Message();
        message.setId(12345678910L);
        message.setContent("hello");
        long id = message.getId();
        String content = message.getContent();
        Assert.assertEquals(id, 12345678910L);
        Assert.assertEquals(content, "hello");
    }

    @Test
    void toStringTest() {
        Message message = new Message();
        message.setId(12345678910L);
        message.setContent("hello");
        Assert.assertEquals(message.toString(), "Message{id='12345678910', content=hello}" );
    }
}