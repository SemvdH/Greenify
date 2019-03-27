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
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

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

    @Test
    public void getFootprint() throws Exception {
        Float estimate = new Float(5);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getFootprint?name=Eric"),
                Float.class))
                .thenReturn(estimate);
        Float result = userService.getFootprint("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void setInputTest() throws Exception {
        userService.updateInput("Eric", "input_size", "5");
        Mockito.verify(userService).updateInput("Eric", "input_size", "5");
    }

    @Test
    public void addFriendTest() throws Exception {
        userService.addFriend("Eric", "Ceren");
        Mockito.verify(userService).addFriend("Eric", "Ceren");
    }
}


