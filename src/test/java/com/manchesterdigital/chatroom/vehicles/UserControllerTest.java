package com.manchesterdigital.chatroom.vehicles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static final String USER = "{\n\"username\": \"jim\"\n}";
    private static final String USER_URL = "/user";

    @Inject
    private TestRestTemplate restTemplate;

    @Test
    public void given_iAddAUser_then_iGetAUser() throws URISyntaxException {

        ResponseEntity<String> response = restTemplate.postForEntity(USER_URL, USER, String.class);

        // Response should be success, with location, with correct body
        assertEquals(201, response.getStatusCode().value() );
        URI location = response.getHeaders().getLocation();

        // Should be able to get all users and see this one
        ResponseEntity<String> users = restTemplate.getForEntity(USER_URL, String.class);
        assertEquals(HttpStatus.OK, users.getStatusCode());
        assertTrue(users.getBody().contains("jim"));
    }

}