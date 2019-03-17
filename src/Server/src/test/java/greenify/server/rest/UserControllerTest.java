//package greenify.server.rest;
//
//import greenify.common.UserDTO;
//import greenify.server.data.model.User;
//import greenify.server.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void getVehicleWhenRequestingTextShouldReturnMakeAndModel() throws Exception {
//        given(this.userService.loginUser("name", "password"))
//                .willReturn(new UserDTO(1L, "name"));
//        this.mvc.perform(get("/loginUser").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(content().json("name=name, password=password"));
//    }
//
//
//    @Test
//    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
//        User alex = new User(1L, "alex", "password", 0);
//        UserDTO user = userService.loginUser("alex", "password");
//        given(userService.loginUser("alex", "password")).willReturn(user);
//        mvc.perform(get("/loginUser")
//                .contentType(MediaType.ALL))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect((ResultMatcher) jsonPath("$[0].name", is(alex.getName())))
//                .andExpect((ResultMatcher) jsonPath("$[0].password", is(alex.getPassword())));
//    }
//}