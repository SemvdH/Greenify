package greenify.client.rest;

import greenify.common.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public UserDto currentUser;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * registers the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a built userDTO with the required information
     */
    @SuppressWarnings("Duplicates")
    public UserDto registerUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/registerUser")
                .queryParam("name", name)
                .queryParam("password", password);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        UserDto result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), UserDto.class);
        this.currentUser = result;
        return result;
    }

    /**
     * logd the user in.
     * @param name the username of the user
     * @param password the password of the user
     * @return a built userDTO with the required information
     */
    @SuppressWarnings("Duplicates")
    public UserDto loginUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/loginUser")
                .queryParam("name", name)
                .queryParam("password", password);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        UserDto result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), UserDto.class);
        this.currentUser = result;
        return result;
    }

    /**
     * adds a vegetarian meal to the user.
     * @param id the id of the user
     * @param name the username of the user
     * @return a built userDTO with the required information
     */
    @SuppressWarnings("Duplicates")
    public UserDto addVeganMeal(Long id, String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/addVeganMeal")
                .queryParam("id", id)
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDto.class);
    }

    /**
     * gets the username from the user.
     * @param id the id of the user
     * @return a UserDTO with the required information
     */
    @SuppressWarnings("Duplicates")
    public UserDto getName(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getUsername")
                .queryParam("id", id);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDto.class);
    }
}
