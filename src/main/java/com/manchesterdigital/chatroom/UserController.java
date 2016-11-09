package com.manchesterdigital.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @Inject
    FirebaseService firebaseService;

    private static final Logger logger = LoggerFactory
            .getLogger(MessageController.class);

    @RequestMapping(method=RequestMethod.GET, produces = "Application/json", value="user")
    public @ResponseBody
    Map<String, String> getAllUsers() {

        Map<String, String> allUsers = new HashMap<>();
        allUsers.putAll(firebaseService.getAllUsers());

        logger.debug("Returning {} users", allUsers.size());

        return allUsers;
    }

    @RequestMapping(method= RequestMethod.POST, produces = "Application/json", value="user")
    public ResponseEntity<?> addUser(@RequestBody String user) {

        String userId = firebaseService.addUser(user);

        // Response
        Map<String,Object> model = new HashMap<>();

        model.put("id", userId);
        model.put("content", user);

        logger.debug("created user ID {}", userId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

}
