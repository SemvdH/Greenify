package greenify.client.rest;

import greenify.common.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * registers the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO
     */
    public UserDTO registerUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/registerUser")
                .queryParam("name", name)
                .queryParam("password", password);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDTO.class);
    }

    public UserDTO loginUser(String name, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/loginUser")
                .queryParam("name", name)
                .queryParam("password", password);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDTO.class);
    }

    public UserDTO addVeganMeal(Long id, String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/addVeganMeal")
                .queryParam("id", id)
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDTO.class);
    }
}
