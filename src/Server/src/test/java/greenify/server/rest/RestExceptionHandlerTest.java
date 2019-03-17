package greenify.server.rest;

import static org.junit.Assert.assertEquals;

import greenify.common.ApplicationException;
import greenify.common.ErrorResponse;
import org.junit.Test;

public class RestExceptionHandlerTest {
    @Test
    public void test() {
        ApplicationException ex = new ApplicationException("testing");
        ErrorResponse response = new ErrorResponse("testing");
        assertEquals(RestExceptionHandler.applicationException(ex)
                .getMessage(), response.getMessage());
    }
}