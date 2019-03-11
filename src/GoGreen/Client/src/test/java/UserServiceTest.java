import gogreen.client.rest.UserService;
import gogreen.common.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    @Spy
    UserService userService;

    @Test
    public void mocking() throws Exception {
        UserDTO testUser = new UserDTO(1L, "Eric");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/registerUser?name=Eric&password=password"),
                UserDTO.class))
                .thenReturn(testUser);

        UserDTO user = userService.registerUser("Eric", "password");
        Assert.assertEquals(testUser, user);
    }
}


