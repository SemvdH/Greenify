package greenify.client.rest;

import greenify.common.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    public UserDto currentUser;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Registers the user.
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
        //set the accept header in JSON value
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        //connect to the server with the needed url
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/registerUser")
                .queryParam("name", name)
                //getting the name from the database
                .queryParam("password", password);
        //getting the password from the database

        //create a http entity to be sent
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());

        //the result to be sent is a userDto
        //encodes the userDTO object to a Uri so the database can work with it
        UserDto result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), UserDto.class);
        this.currentUser = result;
        return result;
    }

    /**
     * Signs in the user.
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
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        UserDto result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), UserDto.class);
        this.currentUser = result;
        return result;
    }

    /**
     * Updates the input of the user.
     * @param name name of the user
     * @param inputName name of the input
     * @param value value of the input
     */
    @SuppressWarnings("Duplicates")
    public void updateInput(String name, String inputName, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/setInput")
                .queryParam("name", name)
                .queryParam("inputName", inputName)
                .queryParam("value",value);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        ResponseEntity<String> authenticateResponse = this.restTemplate.getForEntity(builder.build()
                .encode().toUri(), String.class);
    }

    /**
     * Updates the extra input of the user.
     * @param name name of the user
     * @param inputName name of the input
     * @param value value of the input
     */
    @SuppressWarnings("Duplicates")
    public void updateExtraInput(String name, String inputName, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/setExtraInput")
                .queryParam("name", name)
                .queryParam("inputName", inputName)
                .queryParam("value", value);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        ResponseEntity<String> authenticateResponse = this.restTemplate.getForEntity(builder.build()
                .encode().toUri(), String.class);
    }

    /**
     * Gets the footprint of the user.
     * @param name name of the user
     * @return returns the footprint score
     */
    @SuppressWarnings("Duplicates")
    public double getFootprint(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getFootprint")
                .queryParam("name", name);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Float footprint = this.restTemplate.getForObject(builder
                .build().encode().toUri(), Float.class);
        double result = Math.round(footprint * 10) / 10.0;
        return result;
    }

    /**
     * Gets the first footprint of the user.
     * @param name name of the user
     * @return returns the footprint score
     */
    public double getFirstFootprint(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getFirst")
                .queryParam("name", name);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Float footprint = this.restTemplate.getForObject(builder
                .build().encode().toUri(), Float.class);
        double result = Math.round(footprint * 10) / 10.0;
        return result;
    }

    /**
     * Saves the footprint of the user.
     * @param name name of the user
     * @return returns the footprint score
     */
    @SuppressWarnings("Duplicates")
    public Float saveFootprint(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/saveFootprint")
                .queryParam("name", name);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Float result = this.restTemplate.getForObject(builder
                .build().encode().toUri(), Float.class);
        return result;
    }


    /**
     * Saves the first footprint of the user.
     * @param name name of the user
     * @return returns the footprint score
     */
    @SuppressWarnings("Duplicates")
    public Float saveFirstFootprint(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/saveFirstFootprint")
                .queryParam("name", name);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Float result = this.restTemplate.getForObject(builder
                .build().encode().toUri(), Float.class);
        return result;
    }

    /**
     * Gets the friend list of the user.
     * @param name name of the user
     * @return returns the friend list
     */
    @SuppressWarnings("Duplicates")
    public List<String> getFriendNames(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getFriends")
                .queryParam("name", name);
        new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        List<String> result = this.restTemplate.getForObject(builder
                .build().encode().toUri(), List.class);
        return result;
    }

    /**
     * Adds a friend to the user.
     * @param name the username of the current user.
     * @param friend the username of the friend you want to add.
     */
    @SuppressWarnings("Duplicates")
    public void addFriend(String name, String friend) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/addFriend")
                .queryParam("name", name)
                .queryParam("friend",friend);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        ResponseEntity<String> authenticateResponse = this.restTemplate.getForEntity(builder.build()
                .encode().toUri(), String.class);
    }

    /**
     * Removes a friend from the friendslist of the user.
     * @param name the username of the current user.
     * @param friend the username of the friend you want to remove.
     */
    @SuppressWarnings("Duplicates")
    public void removeFriend(String name, String friend) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/removeFriend")
                .queryParam("name", name)
                .queryParam("friend",friend);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        ResponseEntity<String> authenticateResponse = this.restTemplate.getForEntity(builder.build()
                .encode().toUri(), String.class);
    }

    /**
     * Gets the footprint inputs of the user.
     * @param name the username of the current user.
     */
    @SuppressWarnings("Duplicates")
    public Map<String, String> getInputs(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getInputs")
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Map<String, String> result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), Map.class);
        return result;
    }

    /**
     * Gets the footprint inputs of the user.
     * @param name the username of the current user.
     */
    @SuppressWarnings("Duplicates")
    public Map<String, String> getExtraInputs(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getExtraInputs")
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        Map<String, String> result = this.restTemplate.getForObject(builder.build()
                .encode().toUri(), Map.class);
        return result;
    }

    /**
     * Gets the achievements of a user.
     * @param name name of the user
     * @return Map with all achievements
     */
    @SuppressWarnings("Duplicates")
    public Map getAchievements(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getAchievements")
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build()
                .encode().toUri(), Map.class);
    }

    /**
     * Gets the results of a user.
     * @param name name of the user
     * @return Map with all results
     */
    @SuppressWarnings("Duplicates")
    public Map<String, String> getResults(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getResults")
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        return this.restTemplate.getForObject(builder.build()
                .encode().toUri(), Map.class);
    }

    /**
     * Gets the list of all users.
     */
    @SuppressWarnings("Duplicates")
    public List<String> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getAllUsers");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        List<String> result = this.restTemplate.getForObject(builder
                .build().encode().toUri(), List.class);
        return result;
    }

    /**
     * Removes a user from the database.
     * @param name the username of the current user.
     */
    @SuppressWarnings("Duplicates")
    public void deleteAccount(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/deleteAccount")
                .queryParam("name", name);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        System.out.println(builder.build().encode().toUri());
        ResponseEntity<String> authenticateResponse = this.restTemplate.getForEntity(builder.build()
                .encode().toUri(), String.class);
    }
}