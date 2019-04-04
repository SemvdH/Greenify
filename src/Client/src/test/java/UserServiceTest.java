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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void getFirstFootprint() throws Exception {
        Float estimate = new Float(5);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getFirst?name=Eric"),
                Float.class))
                .thenReturn(estimate);
        Float result = userService.getFirstFootprint("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void saveFootprint() throws Exception {
        Float estimate = new Float(5);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/saveFootprint?name=Eric"),
                Float.class))
                .thenReturn(estimate);
        Float result = userService.saveFootprint("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void saveFirstFootprint() throws Exception {
        Float estimate = new Float(5);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/saveFirstFootprint?name=Eric"),
                Float.class))
                .thenReturn(estimate);
        Float result = userService.saveFirstFootprint("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void getFriendNamesTest() throws Exception {
        List<String> estimate = new ArrayList<String>();
        estimate.add("alex");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getFriends?name=Eric"),
                List.class))
                .thenReturn(estimate);
        List<String> result = userService.getFriendNames("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void getAllUsers() throws Exception {
        List<String> estimate = new ArrayList<String>();
        estimate.add("alex");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getAllUsers"),
                List.class))
                .thenReturn(estimate);
        List<String> result = userService.getAllUsers();
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void getInputsTest() throws Exception {
        Map<String, String> estimate = new HashMap<>();
        estimate.put("Eric", "3");
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getInputs?name=Eric"),
                Map.class))
                .thenReturn(estimate);
        Map<String, String> result = userService.getInputs("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void getExtraInputsTest() throws Exception {
        Map<String, Boolean> estimate = new HashMap<>();
        estimate.put("solar_panels", true);
        Mockito.when(restTemplate.getForObject(new java.net.URI("http://localhost:8080/getExtraInputs?name=Eric"),
                Map.class))
                .thenReturn(estimate);
        Map<String, Boolean> result = userService.getExtraInputs("Eric");
        Assert.assertEquals(estimate, result);
    }

    @Test
    public void setInputTest() throws Exception {
        userService.updateInput("Eric", "input_size", "5");
        Mockito.verify(userService).updateInput("Eric", "input_size", "5");
    }

    @Test
    public void setExtraInputTest() throws Exception {
        userService.updateExtraInput("Eric", "solar_panels", true);
        Mockito.verify(userService).updateExtraInput("Eric", "solar_panels", true);
    }

    @Test
    public void addFriendTest() throws Exception {
        userService.addFriend("Eric", "Ceren");
        Mockito.verify(userService).addFriend("Eric", "Ceren");
    }

    @Test
    public void removeFriendTest() throws Exception {
        userService.addFriend("Eric", "Ceren");
        Mockito.verify(userService).addFriend("Eric", "Ceren");
        userService.removeFriend("Eric", "Ceren");
        Mockito.verify(userService).removeFriend("Eric", "Ceren");
    }

    @Test
    public void getAchievementsTest() throws Exception {
        userService.getAchievements("mika");
        Mockito.verify(userService).getAchievements("mika");
    }
}


