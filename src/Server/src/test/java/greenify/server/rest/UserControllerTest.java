package greenify.server.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import greenify.common.UserDto;
import greenify.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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

    @Test
    public void setInputTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg3Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/setInput")
                .param("name", "ceren")
                .param("inputName", "input_size")
                .param("value", "7")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .setInput(arg1Captor.capture(), arg2Captor.capture(), arg3Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
        assertEquals("input_size", arg2Captor.getValue());
        assertEquals("7", arg3Captor.getValue());
    }

    @Test
    public void setExtraInputTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Boolean> arg3Captor = ArgumentCaptor.forClass(Boolean.class);
        mvc.perform(get("/setExtraInput")
                .param("name", "ceren")
                .param("inputName", "input_size")
                .param("value", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .setExtraInput(arg1Captor.capture(), arg2Captor.capture(), arg3Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
        assertEquals("input_size", arg2Captor.getValue());
        assertEquals(true, arg3Captor.getValue());
    }

    @Test
    public void addFriendTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/addFriend")
                .param("name", "ceren")
                .param("friend", "merel")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .addFriend(arg1Captor.capture(), arg2Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
        assertEquals("merel", arg2Captor.getValue());
    }

    @Test
    public void removeFriendTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/removeFriend")
                .param("name", "ceren")
                .param("friend", "merel")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .removeFriend(arg1Captor.capture(), arg2Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
        assertEquals("merel", arg2Captor.getValue());
    }

    @Test
    public void getInputMapTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getInputs")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .getInputMap(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void getExtraInputMapTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getExtraInputs")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1))
                .getExtraInputMap(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void getAllUsersTest() throws Exception {
        mvc.perform(get("/getAllUsers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getFriendNamesTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getFriends")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).getFriends(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void getInputTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getInput")
                .param("name", "ceren")
                .param("inputName", "input_size")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).getInput(arg1Captor.capture(), arg2Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
        assertEquals("input_size", arg2Captor.getValue());
    }

    @Test
    public void getFootprintTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getFootprint")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).getFootprint(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void getFirstFootprintTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getFirst")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).getFirstFootprint(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void saveFootprintTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/saveFootprint")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).saveFootprint(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void saveFirstFootprintTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/saveFirstFootprint")
                .param("name", "ceren")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).saveFirstFootprint(arg1Captor.capture());
        assertEquals("ceren", arg1Captor.getValue());
    }

    @Test
    public void getAchievementsTest() throws Exception {
        ArgumentCaptor<String> arg1Captor = ArgumentCaptor.forClass(String.class);
        mvc.perform(get("/getAchievements")
                .param("name", "mika")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).getAchievements(arg1Captor.capture());
        assertEquals("mika", arg1Captor.getValue());
    }

}
