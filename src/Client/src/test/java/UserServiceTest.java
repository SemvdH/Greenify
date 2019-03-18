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
    //logger that logs everything to the console
    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    @Spy
    UserService userService;

    @Test
    public void userRegisterTest() throws Exception {
        //tests if registering works
        UserDto testUser = new UserDto(1L, "Eric", 0);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/registerUser?name=Eric&password=password"),
                UserDto.class))
                .thenReturn(testUser);

        UserDto user = userService.registerUser("Eric", "password");
        Assert.assertEquals(testUser, user);
    }

    @Test
    public void userLoginTest() throws Exception {
        //tests if logging in works
        UserDto testUser = new UserDto(1L, "Eric", 0);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/loginUser?name=Eric&password=password"),
                UserDto.class))
                .thenReturn(testUser);
        UserDto user = userService.loginUser("Eric", "password");
        Assert.assertEquals(testUser, user);
    }

    @Test
    public void addVeganMealTest() throws Exception {
        //tests if adding a vegetarian meal works
        UserDto testUser = new UserDto(1L, "Eric", 0);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/addVeganMeal?id=1&name=Eric"),
                UserDto.class))
                .thenReturn(testUser);
        UserDto user = userService.addVeganMeal(1L, "Eric");
        Assert.assertEquals(testUser, user);
    }
}


