import hello.Greeting;
import hello.GreetingController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GreetingControllerTest {
    @Test
    void nullTest() {
        GreetingController controller = new GreetingController();
        assertThat(controller).isNotNull();
    }
}

