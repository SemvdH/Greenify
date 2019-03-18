package greenify.client.rest;

import greenify.common.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @return a userDTO
     */
    @SuppressWarnings("Duplicates")
    //this suppressWarnings is to get rid of the errors of duplicate code
    //because the methods are very similar
    public UserDto registerUser(String name, String password) {
        //headers for http
        HttpHeaders headers = new HttpHeaders();
        //set the accept header in JSÖN value
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        //connect to the server with the needed url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/registerUser")
                .queryParam("name", name)
                //getting the name from the database
                .queryParam("password", password);
        //getting the password from the database

        //create a http entity to be sent
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());

        //the result to be sent is a userDto
        UserDto result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), UserDto.class);
        this.currentUser = result;
        return result;
    }

    /**
     * sign ins the user.
     * @param name the username of the user
     * @param password the password of the user
     * @return a userDTO
     */
    @SuppressWarnings("Duplicates")
    public UserDto loginUser(String name, String password) {
        //this method is almost the same as the registerUser one, but with a different link
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
     * a user adds vegan meal.
     * @param id the id of the user
     * @param name the username of the user
     * @return a userDTO
     */
    @SuppressWarnings("Duplicates")
    public UserDto addVeganMeal(Long id, String name) {
        //this method is almost the same as the registerUser one, but with a different link
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/addVeganMeal")
                .queryParam("id", id)
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build().encode().toUri(), UserDto.class);
    }

    @RequestMapping("/userData")
    public int getVeganData(@RequestParam(value = "veganMeal") int veganMeal) {
        return veganMeal;
    }
}
