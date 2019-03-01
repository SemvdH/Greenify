import hello.Greeting;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GreetingTest {
    @Test
    void testGets() {
        Greeting greeting = new Greeting(1, "hello");
        long id = greeting.getId();
        String content = greeting.getContent();
        assertThat(id).isEqualTo(1);
        assertThat(content).isEqualTo(content);
    }
}