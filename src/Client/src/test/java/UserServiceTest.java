import greenify.client.rest.UserService;
import greenify.common.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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
    public void userRegisterTest() throws Exception {
        UserDto testUser = new UserDto(1L, "Eric");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/registerUser?name=Eric&password=password"),
                UserDto.class))
                .thenReturn(testUser);

        UserDto user = userService.registerUser("Eric", "password");
        Assert.assertEquals(testUser, user);
    }

    @Test
    public void userLoginTest() throws Exception {
        UserDto testUser = new UserDto(1L, "Eric");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/loginUser?name=Eric&password=password"),
                UserDto.class))
                .thenReturn(testUser);
        UserDto user = userService.loginUser("Eric", "password");
        Assert.assertEquals(testUser, user);
    }
}


