package Client.rest;

import Common.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

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
}
