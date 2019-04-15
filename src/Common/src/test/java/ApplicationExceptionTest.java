import static org.junit.Assert.assertEquals;

import greenify.common.ApplicationException;
import org.junit.Test;

public class ApplicationExceptionTest {

    @Test
    public void setAndGetTest() {
        ApplicationException ex = new ApplicationException("This is an exception");
        assertEquals(ex.getMessage(), "This is an exception");
    }

    @Test
    public void equalsTest() {
        ApplicationException ex = new ApplicationException("This is an exception");
        ApplicationException test = new ApplicationException("This is an exception");
        assertEquals(ex.getMessage(), test.getMessage());
    }

}
