package test.java;

import main.java.hello.GreetingController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GreetingControllerTest {
    @Test
    void nullTest() {
        GreetingController controller = new GreetingController();
//        Assert.assertThat(controller).isNotNull();
        Assert.assertNotNull(controller);
    }
}

