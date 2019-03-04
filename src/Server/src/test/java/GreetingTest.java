package test.java;

import main.java.hello.Greeting;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GreetingTest {
    @Test
    void testGets() {
        Greeting greeting = new Greeting(1, "hello");
        long id = greeting.getId();
        String content = greeting.getContent();
//        assertThat(id).isEqualTo(1);
        Assert.assertEquals(id, 1);
//        assertThat(content).isEqualTo(content);
        Assert.assertEquals(content,content);
    }
}