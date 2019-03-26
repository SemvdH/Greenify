package greenify.server.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import greenify.common.UserDto;
import greenify.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private UserService userService;

    @Test
    public void registerUserTest() throws Exception {
        given(this.userService.registerUser("name", "password"))
                .willReturn(new UserDto(1L, "name"));
        mvc.perform(get("/registerUser")
                .param("name", "name")
                .param("password", "password")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("{'id':1,'name':'name'}"));
    }

    @Test
    public void loginUserTest() throws Exception {
        given(this.userService.loginUser("ceren", "password"))
                .willReturn(new UserDto(1L, "ceren"));
        mvc.perform(get("/loginUser")
                .param("name", "ceren")
                .param("password", "password")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("{'id':1,'name':'ceren'}"));
    }

    //@Test
    //public void setInputTest() {
    //
    //}
}
