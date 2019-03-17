import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import greenify.common.ErrorResponse;

import org.junit.Test;

public class ErrorResponseTest {
    @Test
    public void setAndGetTest() {
        ErrorResponse response = new ErrorResponse("New error");
        ErrorResponse testResponse = new ErrorResponse();
        testResponse.setMessage("New error");
        assertTrue(response.getMessage().equals("New error"));
    }

    @Test
    public void equalsTest() {
        ErrorResponse first = new ErrorResponse("New error");
        ErrorResponse second = new ErrorResponse("New error");
        assertEquals(first.getMessage(), second.getMessage());
        assertTrue(first.getMessage().equals(second.getMessage()));
    }
}