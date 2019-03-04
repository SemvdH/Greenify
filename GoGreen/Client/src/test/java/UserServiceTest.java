//import gogreen.client.rest.UserService;
//import gogreen.common.UserDTO;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//    private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
//
//    @Mock
//    RestTemplate restTemplate;
//
//    @InjectMocks
//    @Spy
//    UserService userService;
//
//    @Test
//    public void mocking() {
//        UserDTO testUser = new UserDTO(1L, "Eric Simmons");
//        Mockito.when(restTemplate.getForObject("http://localhost:8080/registerUser?name=Eric%20Simmons&password=password", UserDTO.class))
//                .thenReturn(testUser);
//
//        UserDTO user = userService.registerUser("Eric Simmons", "password");
//        Assert.assertEquals(testUser, user);
//    }
//}


