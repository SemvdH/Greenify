import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import greenify.client.Hints;
import org.junit.Test;

public class HintsTest {
    @Test
    public void initHintsTest() {
        Hints test = new Hints();
        assertFalse(test.hints.isEmpty());
    }

    @Test
    public void hintsContainsTest() {
        Hints test = new Hints();
        assertTrue(test.hints.contains("27,000 trees are cut down "
                + "every day so we can have toilet paper."));
    }

    @Test
    public void randomHintTest() {
        Hints test = new Hints();
        String random = test.randomHint();
        assertTrue(test.hints.contains(random));
    }

    @Test
    public void hintsContainsTestTwo() {
        Hints test = new Hints();
        assertTrue(test.hints.contains("Plastic bad, very bad."));
    }


    @Test
    public void hintsNotNullTest() {
        Hints test = new Hints();
        assertNotNull(test.hints);
    }

}


