package gogreen.server.rest;

import gogreen.common.UserDTO;
import gogreen.server.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void registerUserTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/registerUser")
                .queryParam("name", "ceren")
                .queryParam("password", "password");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UserDTO user = this.restTemplate.getForObject(builder.build().encode().toUri(), UserDTO.class);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId().longValue(), 1L);
        try {
            user = this.restTemplate.getForObject(builder.build().encode().toUri(), UserDTO.class);
            Assert.fail("Error should be reported");
        } catch (HttpStatusCodeException exception) {
            int statusCode = exception.getStatusCode().value();
            Assert.assertEquals(statusCode, 400);
            Assert.assertTrue(exception.getResponseBodyAsString().contains("User already exists"));
        }
    }
}